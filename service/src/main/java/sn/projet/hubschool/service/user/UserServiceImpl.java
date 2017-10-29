package sn.projet.hubschool.service.user;

import sn.projet.hubschool.dao.UserDao;
import sn.projet.hubschool.model.Language;
import sn.projet.hubschool.model.user.User;
import sn.projet.hubschool.service.BaseServiceImpl;
import sn.projet.hubschool.transverse.SpringMVCConstants;

import freemarker.template.Configuration;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    private static final String NONE_CONFUSING_CHARSET = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz23456789";

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${mail.fromAddress}")
    private String fromAddress;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration configuration;

    @Override
    public void delete(final User user) {
        userDao.delete(user);
    }

    @Override
    public User saveOrUpdate(final User user) {
        if (user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getNewPassword()));
        } else if (StringUtils.isNotBlank(user.getNewPassword())) {
            user.setPassword(passwordEncoder.encode(user.getNewPassword()));
        }
        return userDao.save(user);
    }

    @Override
    public User loadUserByUsername(final String username) {
        final User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username " + username + " not found");
        }

        return user;
    }

    @Override
    public void resetUserPassword(final User user) {
        user.setNewPassword(RandomStringUtils.random(7, NONE_CONFUSING_CHARSET));
        saveOrUpdate(user);
    }

    @Override
    public boolean forgotUserPassword(final String email) {
        boolean found = false;
        final User user = loadUserByUsername(email);
        if (user != null) {
            found = true;
            resetUserPassword(user);
        }
        return found;
    }

    @Override
    public void delete(final Long id, final String username) {
        final User loggedUser = loadUserByUsername(username);
        final User user = load(id);
        if (user.getId().equals(loggedUser.getId())) {
            throw new AccessDeniedException("Not allowed to view or update user");
        }
        user.setEnabled(false);
    }

    @Override
    @Transactional
    public boolean registerNewUser(final String email, final String firstname, final String lastname, final Locale locale) {
        if (userDao.findByUsername(email) != null) {
            // user already exist
            return false;
        }

        final String[] roles = {SpringMVCConstants.ROLE_CUSTUMER};
        final String password = RandomStringUtils.random(7, NONE_CONFUSING_CHARSET);

        final User user = new User(email, password, firstname, lastname, true, roles, Language.valueOf(locale.getLanguage()));
        saveOrUpdate(user);

        mailSender.send(new RegisterMimeMessagePreparator(configuration, messageSource, fromAddress, user.getUsername(), user,
                password, locale));

        return true;
    }

    @Override
    public User load(final Long id) {
        return userDao.findOne(id);
    }

    @Override
    public List<User> getList() {
        return userDao.findAll();
    }

    @Override
    public User loadEager(final Long id) {
        final User user = userDao.findOne(id);
        if (user != null) {
            Hibernate.initialize(user);
            Hibernate.initialize(user.getAuthorities());
        }
        return user;
    }

    @Override
    public void deleteById(final Long id) {
        userDao.delete(id);
    }

    private static final class RegisterMimeMessagePreparator implements MimeMessagePreparator {

        private final Configuration configuration;
        private final MessageSource messageSource;
        private final String fromAddress;
        private final String toAddress;
        private final User user;
        private final String password;
        private final Locale locale;

        public RegisterMimeMessagePreparator(final Configuration configuration, final MessageSource messageSource,
                                             final String fromAddress, final String toAddress, final User user, final String password, final Locale locale) {
            this.configuration = configuration;
            this.messageSource = messageSource;
            this.fromAddress = fromAddress;
            this.toAddress = toAddress;
            this.user = user;
            this.password = password;
            this.locale = locale;
        }

        @Override
        public void prepare(final MimeMessage mimeMessage) throws Exception {
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(toAddress);
            message.setFrom(fromAddress);

            final Map<String, Object> model = new HashMap<String, Object>();
            model.put("user", user);
            model.put("password", password);

            final String text = FreeMarkerTemplateUtils.processTemplateIntoString(
                    configuration.getTemplate("mail/register_" + locale.getLanguage() + ".ftl", "UTF-8"), model);
            message.setText(text, true);
            message.setSubject(messageSource.getMessage("mail.register.subject", null, locale));
        }
    }

}

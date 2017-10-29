package sn.projet.hubschool.authentication;

import sn.projet.hubschool.model.user.User;
import sn.projet.hubschool.service.user.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Log LOG = LogFactory.getLog(AuthenticationSuccessHandler.class);

    @Autowired
    private UserService userService;


    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws ServletException, IOException {

        if (LOG.isInfoEnabled()) {
            LOG.info("onAuthenticationSuccess");
        }

        if (authentication.getPrincipal() instanceof User) {
            final User user = (User) authentication.getPrincipal();
            user.setLastLoginTime(new Date());
            userService.saveOrUpdate(user);
        }


        super.onAuthenticationSuccess(request, response, authentication);
    }
}

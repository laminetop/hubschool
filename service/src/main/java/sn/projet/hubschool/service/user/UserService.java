package sn.projet.hubschool.service.user;

import sn.projet.hubschool.model.user.User;
import sn.projet.hubschool.service.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

public interface UserService extends BaseService < User, Long >, UserDetailsService {

	void resetUserPassword(User user);

	boolean forgotUserPassword(String email);

	void delete(Long id, String username);

	@Transactional
	boolean registerNewUser(String email, String firstname, String lastname, Locale locale);

	User loadEager(Long id);

}

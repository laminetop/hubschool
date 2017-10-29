package sn.projet.hubschool.facade.user;

import sn.projet.hubschool.facade.BaseFacade;
import sn.projet.hubschool.facade.dto.user.UserVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Locale;

public interface UserFacade extends BaseFacade < UserVO, Long >, UserDetailsService {

	void resetUserPassword(UserVO user);

	boolean forgotUserPassword(String email);

	void delete(Long id, String username);

	boolean registerNewUser(String email, String firstname, String lastname, Locale locale);

	UserVO loadEager(Long id);

}

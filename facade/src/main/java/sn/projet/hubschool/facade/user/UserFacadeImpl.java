package sn.projet.hubschool.facade.user;

import sn.projet.hubschool.facade.BaseFacadeImpl;
import sn.projet.hubschool.facade.dto.user.UserVO;
import sn.projet.hubschool.facade.utils.DozerListTransformer;
import sn.projet.hubschool.model.user.User;
import sn.projet.hubschool.service.user.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component("userFacade")
public final class UserFacadeImpl extends BaseFacadeImpl < UserVO, Long > implements UserFacade {

	@Autowired
	private UserService userService;

	@Autowired
	public DozerBeanMapper dozerBeanMapper;

	@Override
	public void delete(final UserVO user) {
		User entite = userService.load(user.getId());
		dozerBeanMapper.map(user, entite);
		userService.delete(entite);
	}

	@Override
	public UserVO saveOrUpdate(final UserVO user) {
		User entite = null;
		if (user.getId() == null) {
			entite = dozerBeanMapper.map(user, User.class);
		} else {
			entite = userService.load(user.getId());
			dozerBeanMapper.map(user, entite);
		}

		userService.saveOrUpdate(entite);

		return dozerBeanMapper.map(entite, UserVO.class);
	}

	@Override
	public UserVO loadUserByUsername(final String username) {
		User entite = (User) userService.loadUserByUsername(username);
		UserVO user = null;
		if (entite != null) {
			user = dozerBeanMapper.map(entite, UserVO.class);
		}
		return user;
	}

	@Override
	public void resetUserPassword(final UserVO user) {
		User entite = userService.load(user.getId());
		dozerBeanMapper.map(user, entite);
		userService.resetUserPassword(entite);

	}

	@Override
	public boolean forgotUserPassword(final String email) {
		return userService.forgotUserPassword(email);
	}

	@Override
	public void delete(final Long id, final String username) {
		userService.delete(id, username);
	}

	@Override
	public boolean registerNewUser(final String email, final String firstname, final String lastname, final Locale locale) {

		return userService.registerNewUser(email, firstname, lastname, locale);
	}

	@Override
	public UserVO load(final Long id) {
		User entite = userService.load(id);
		UserVO user = null;
		if (entite != null) {
			user = dozerBeanMapper.map(entite, UserVO.class);
		}
		return user;
	}

	@Override
	public List < UserVO > getList() {
		List < UserVO > userVOs = new ArrayList <>();
		DozerListTransformer dozerListTransformer = new DozerListTransformer(UserVO.class, dozerBeanMapper);
		for (User declaration : userService.getList()) {
			userVOs.add((UserVO) dozerListTransformer.transform(declaration));
		}
		return userVOs;
	}

	@Override
	public UserVO loadEager(final Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(final Long id) {
		userService.deleteById(id);
	}

}

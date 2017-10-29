package sn.projet.hubschool.validator.user;

import sn.projet.hubschool.model.user.User;
import sn.projet.hubschool.service.user.UserService;
import sn.projet.hubschool.validator.BaseValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator extends BaseValidator implements Validator {

	private final UserService userService;

	public UserValidator(final UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(final Class < ? > clazz) {
		return clazz.equals(User.class);
	}

	@Override
	public void validate(final Object object, final Errors errors) {

		final User user = (User) object;

		// Case of updating user
		if (user.getId() != null) {
			final User existingUser = userService.load(user.getId());

			if (existingUser != null
				&& (StringUtils.isNotEmpty(user.getNewPassword()) || StringUtils.isNotEmpty(user.getConfirmPassword()))) {

				ValidationUtils.rejectIfEmpty(errors, "newPassword", "NotBlank");
				ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "NotBlank");

				if (!StringUtils.equals(user.getNewPassword(), user.getConfirmPassword())) {
					errors.rejectValue("newPassword", "error.password.no_match");
				}
			}

		} else {
			if (!errors.hasFieldErrors("username")) {
				try {
					final User dbUser = (User) userService.loadUserByUsername(user.getUsername());
					if (dbUser != null) {
						errors.rejectValue("username", "username.alreadyExists");
					}
				} catch (final UsernameNotFoundException ex) {
				}
			}

			ValidationUtils.rejectIfEmpty(errors, "newPassword", "NotBlank");
			ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "NotBlank");

			if (!StringUtils.equals(user.getNewPassword(), user.getConfirmPassword())) {
				errors.rejectValue("newPassword", "error.password.no_match");
			}
		}

	}
}

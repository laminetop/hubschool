package sn.projet.hubschool.validator.user;

import sn.projet.hubschool.validator.BaseValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

public class FileImageValidator extends BaseValidator implements Validator {

	public FileImageValidator() {
	}

	@Override
	public boolean supports(final Class<?> clazz) {
		return clazz.equals(MultipartFile.class);
	}

	@Override
	public void validate(final Object object, final Errors errors) {

		final MultipartFile file = (MultipartFile) object;
		if (StringUtils.isEmpty(file.getContentType())) {
			errors.rejectValue("contentType", "error.file.not.image");
		}
		String type = file.getContentType().split("/")[0];

		if (!type.equals("image")) {
			errors.rejectValue("contentType", "error.file.not.image");
		}
	}
}

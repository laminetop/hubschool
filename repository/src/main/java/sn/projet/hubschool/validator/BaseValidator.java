package sn.projet.hubschool.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public abstract class BaseValidator {

	@Autowired
	private MessageSource messageSource;

	private Locale locale;

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(final Locale locale) {
		this.locale = locale;
	}
}

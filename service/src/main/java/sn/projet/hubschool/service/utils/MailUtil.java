package sn.projet.hubschool.service.utils;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration configuration;

	@Autowired
	private MessageSource messageSource;

}

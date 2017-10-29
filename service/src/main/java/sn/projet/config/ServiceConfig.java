package sn.projet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * Created by alygueye on 29/12/2016.
 */
//@formatter:off
@Configuration
@ImportResource({
                "classpath:spring/spring-mail.xml",
                "classpath:spring/spring-resource.xml"})
@PropertySource({"classpath:/application.properties",
                    "classpath:/mail.properties",
                    "classpath:/freemarker.properties"})
@ComponentScan(basePackages = {"sn.projet.hubschool.service",
                                "sn.projet.nomenclature.service",
                                "sn.projet.nomenclature.domaine"},
        includeFilters =@ComponentScan.Filter(Service.class))
//@formatter:on
public class ServiceConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath*:bundles/messages", "WEB-INF/bundles/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(1);
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public freemarker.template.Configuration freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPaths("classpath:/freemarker/", "/WEB-INF/classes/freemarker/");
        freeMarkerConfigurer.setPreferFileSystemAccess(false);
        return freeMarkerConfigurer.getConfiguration();
    }

}

package sn.projet.hubschool.config;


import sn.projet.config.CacheConfig;
import sn.projet.config.FacadeConfig;
import sn.projet.config.RepositoryConfig;
import sn.projet.config.ServiceConfig;
import sn.projet.config.TransverseConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

//@formatter:off
@Configuration
@EnableAsync
@Import({TransverseConfig.class,
        RepositoryConfig.class,
        CacheConfig.class,
        ServiceConfig.class,
        FacadeConfig.class,
        SecurityConfig.class,
        OAuth2ServerConfig.class})
@ComponentScan(basePackages = {"sn.projet.hubschool.filter"})
@PropertySource({
        "classpath:/application.override.properties",
        "classpath:/database.override.properties"})
//@formatter:on
public class AppConfig {

    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

}

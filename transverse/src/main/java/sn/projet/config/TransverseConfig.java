package sn.projet.config;

import sn.projet.exception.ExceptionFactory;
import sn.projet.exception.IExceptionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alygueye on 30/12/2016.
 */
@Configuration
public class TransverseConfig {

    @Bean
    public IExceptionFactory factory() {
        return new ExceptionFactory();
    }
}

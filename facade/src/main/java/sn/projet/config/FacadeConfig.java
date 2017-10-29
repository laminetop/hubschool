package sn.projet.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by alygueye on 30/12/2016.
 */
//@formatter:off
@Configuration
@ComponentScan(basePackages =
    {"sn.projet.hubschool.facade",
    "sn.projet.referentiel.facade"})
//@formatter:on
public class FacadeConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper dozerBeanMapper() {
        return new DozerBeanMapper(Arrays.asList("dozer-vo-mappings.xml"));
    }
}

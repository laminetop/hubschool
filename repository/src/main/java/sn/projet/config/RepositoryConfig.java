package sn.projet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by alygueye on 29/12/2016.
 */
@Configuration
//@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "sn.projet.hubschool.dao,sn.projet.nomenclature.dao")
@EnableJpaAuditing
@ImportResource({"classpath:spring/spring-datasource.xml"})
@PropertySource({"classpath:/database.properties"})
public class RepositoryConfig {

//    @Autowired
//    private Environment env;
}

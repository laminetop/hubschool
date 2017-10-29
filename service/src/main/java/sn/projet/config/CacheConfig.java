package sn.projet.config;

import sn.projet.nomenclature.service.jmx.INomenclatureServiceMBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.MBeanServerFactoryBean;
import org.springframework.jmx.support.RegistrationPolicy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alygueye on 29/12/2016.
 */
//@formatter:off
@Configuration
@EnableCaching
//@EnableMBeanExport(server = )
@ImportResource({
                "classpath:spring/spring-service.xml"})
//@formatter:on
public class CacheConfig {

    @Autowired
    @Qualifier("nomenclatureServiceJMX")
    private INomenclatureServiceMBean nomenclatureServiceJMX;

    @Bean(name = "hubschool-ehCacheMBeanRegistration")
    public MethodInvokingFactoryBean hubschoolEhCacheMBeanRegistration() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("net.sf.ehcache.management.ManagementService.registerMBeans");
        methodInvokingFactoryBean.setArguments(new Object[]{ehcache().getObject(), mbeanServer().getObject(), true, true, true, true});
        return methodInvokingFactoryBean;
    }

    @Bean
    public MBeanServerFactoryBean mbeanServer() {
        MBeanServerFactoryBean mbean = new MBeanServerFactoryBean();
        mbean.setLocateExistingServerIfPossible(true);
        return mbean;
    }

    @Bean
    @Lazy(value = false)
    public MBeanExporter exporter() {
        MBeanExporter exporteur = new MBeanExporter();
        Map<String, Object> beans = new HashMap<>();
        beans.put("sn.projet.nomenclature.service.jmx.impl:type=service,name=nomenclatureService", nomenclatureServiceJMX);
        exporteur.setBeans(beans);
        exporteur.setServer(mbeanServer().getObject());
        exporteur.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
        return exporteur;
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache-spring.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        ehCacheManagerFactoryBean.setAcceptExisting(true);
        ehCacheManagerFactoryBean.setCacheManagerName("hubschool");
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager cacheManager() {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(ehcache().getObject());
        return ehCacheCacheManager;
    }

    @Bean(name = "hubschool-cache")
    public EhCacheFactoryBean hubschoolCache() {
        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
        ehCacheFactoryBean.setCacheManager(ehcache().getObject());
        ehCacheFactoryBean.setCacheName("service");
        return ehCacheFactoryBean;
    }

    @Bean(name = "hubschool-cacheDate")
    public EhCacheFactoryBean hubschoolCacheDate() {
        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
        ehCacheFactoryBean.setCacheManager(ehcache().getObject());
        ehCacheFactoryBean.setCacheName("service_date");
        return ehCacheFactoryBean;
    }
}

package sn.projet.hubschool.config;

import sn.projet.hubschool.interceptors.RequestParamLoggerInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.bean.validators.plugins.NotNullAnnotationPlugin;

@Configuration
@EnableWebMvc
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@Import({SwaggerConfig.class})
@ComponentScan(basePackages = {"sn.projet.hubschool.controller", "sn.projet.hubschool.error"})
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.
                addResourceHandler("/documentation/swagger-ui.html**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.
                addResourceHandler("/documentation/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addRedirectViewController(
                String.join("/docs", env.getProperty("springfox.documentation.swagger.v2.path", "/api-docs")),
                String.join(env.getProperty("springfox.documentation.swagger.v2.path", "/api-docs"), "?group=restful-api")
        );

        registry.addRedirectViewController("/docs/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/docs/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/docs/swagger-resources", "/swagger-resources");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(requestParamLoggerInterceptor())
                .excludePathPatterns("/swagger-resources/**")
        //.addPathPatterns("/api/**", "/oauth/**")
//                //pour ne pas fuiter les login/password des utilisateurs dans nos logs -->
//                .excludePathPatterns("/oauth/**")
        ;
    }

    @Bean
    RequestParamLoggerInterceptor requestParamLoggerInterceptor() {
        return new RequestParamLoggerInterceptor();
    }

    @Bean
    public NotNullAnnotationPlugin notNullPlugin() {
        return new NotNullAnnotationPlugin();
    }
}

package sn.projet.hubschool.config;

import sn.projet.hubschool.authentication.AuthenticationSuccessHandler;
import sn.projet.hubschool.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

@Configuration
@PropertySource({"classpath:/security.properties"})
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers(env.getProperty("springfox.documentation.swagger.v2.path", "/v2/api-docs"), "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**")
                .and()
                .debug(Boolean.valueOf(env.getProperty("auth.web.security.debug.enable", "false")))
                .expressionHandler(new OAuth2WebSecurityExpressionHandler());
    }


    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
        auth
                .userDetailsService(userService)
                    .passwordEncoder(passwordEncoder)
                .and()
                .inMemoryAuthentication().passwordEncoder(passwordEncoder)
                    .withUser(env.getProperty("security.admin.user")).password(env.getProperty("security.admin.pass")).roles(env.getProperty("security.admin.roles"))
                    ;
        // @formatter:on
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .realmName("hubschool-AUTH-Protected-API").and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                    .permitAll()
                .antMatchers("/")
                        .permitAll()
                .antMatchers("/oauth/authorize") // seul le endpoint OAuth2 de consentement est à protéger.
                        .hasAnyAuthority("ROLE_ADMIN","ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .antMatchers("/oauth/**") // les autres endpoints OAuth2 sont publics
                        .permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .permitAll()
                    .successHandler(authenticationSuccessHandler());
        // @formatter:on
    }

}

package sn.projet.hubschool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.Arrays;

import javax.sql.DataSource;

/**
 * Created by alygueye on 03/01/2017.
 */
@Configuration
public class OAuth2ServerConfig {

    private static final String HUBSCHOOL_RESOURCE_ID = "hubschool-app";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private ResourceServerTokenServices tokenServices;

        @Autowired
        private Environment env;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(HUBSCHOOL_RESOURCE_ID).tokenServices(tokenServices);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
			http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .requestMatchers()
            .antMatchers("/**")
            .and()
            .authorizeRequests()
            //.antMatchers("/", "/lib/*", "/images/*", "/css/*", "/swagger-ui.js","/swagger-ui.min.js", "/api-docs", "/fonts/*", "/api-docs/*", "/api-docs/default/*", "/o2c.html","index.html","/webjars/**","/hystrix/**").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/user/register").hasAnyAuthority("ROLE_ADMIN","ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
            .antMatchers("/api/referentiel").access("#oauth2.hasScope('read')")
            .antMatchers("/api/referentiel/**").access("#oauth2.hasScope('read')")
            .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
            .antMatchers(HttpMethod.PATCH, "/api/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.POST, "/api/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.PUT, "/api/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.DELETE, "/api/**").access("#oauth2.hasScope('write')")
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
			// @formatter:on
        }

        /*@Bean
        public TokenStore tokenStore() {
            return new JwtTokenStore(accessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter accessTokenConverter() {
            final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            // converter.setSigningKey("123");
            final Resource resource = new ClassPathResource(env.getProperty("oauth.jwt.publickey.path", "hubschool-public.txt"));
            String publicKey = null;
            try {
                publicKey = IOUtils.toString(resource.getInputStream());
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
            converter.setVerifierKey(publicKey);
            return converter;
        }
        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
            defaultTokenServices.setTokenStore(tokenStore());
            return defaultTokenServices;
        }*/

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private Environment env;

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        @Qualifier("dataSource")
        private DataSource dataSource;

        @Value("classpath:schema.sql")
        private Resource schemaScript;

        @Value("classpath:data.sql")
        private Resource dataScript;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {// @formatter:off
		clients.inMemory()
                //.jdbc(dataSource).passwordEncoder(passwordEncoder)
                            // Confidential client where client secret can be kept safe (e.g. server side)
                    .withClient("confidential").secret("secret")
                    .authorizedGrantTypes("client_credentials", "authorization_code", "refresh_token")
                    .scopes("read", "write")
                    .resourceIds(HUBSCHOOL_RESOURCE_ID)
                    .accessTokenValiditySeconds(3600)
                    .redirectUris("http://localhost:8080/hubschool/client/")

                    .and()

                            // Public client where client secret is vulnerable (e.g. mobile apps, browsers)
                    .withClient("public") // No secret!
                    .authorizedGrantTypes("client_credentials", "implicit")
                    .scopes("read")
                    .resourceIds(HUBSCHOOL_RESOURCE_ID)
                    .redirectUris("http://localhost:8080/hubschool/client/")
                    .accessTokenValiditySeconds(3600)
                    .and()

                            // Trusted client: similar to confidential client but also allowed to handle user password
                    .withClient("trusted").secret("secret")
                    .authorities("ROLE_TRUSTED_CLIENT")
                    .authorizedGrantTypes("client_credentials", "password", "authorization_code", "refresh_token")
                    .scopes("read", "write", "trust")
                    .resourceIds(HUBSCHOOL_RESOURCE_ID)
                    .accessTokenValiditySeconds(3600)
                    .redirectUris("http://localhost:8080/hubschool/client/")
        ;
        // @formatter:on
        }

        @Override
        public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            // @formatter:off
		final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

		endpoints.tokenStore(tokenStore())
				//.accessTokenConverter(accessTokenConverter())
				.tokenEnhancer(tokenEnhancerChain).authenticationManager(authenticationManager);
		// @formatter:on
        }

        @Override
        public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
        }


        @Bean
        public TokenStore tokenStore() {
            return new JwtTokenStore(accessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter accessTokenConverter() {
            final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
             converter.setSigningKey("123");
//            final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
//                    new ClassPathResource(env.getProperty("oauth.jwt.keypair.path", "eticket.jks"))
//                    , env.getProperty("oauth.jwt.keypair.password", "mypass").toCharArray());
//            converter.setKeyPair(keyStoreKeyFactory.getKeyPair(env.getProperty("oauth.jwt.keypair.alias", "eticket")));
            return converter;
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
            defaultTokenServices.setTokenStore(tokenStore());
            defaultTokenServices.setSupportRefreshToken(true);
            return defaultTokenServices;
        }

        @Bean
        public TokenEnhancer tokenEnhancer() {
            return new CustomTokenEnhancer();
        }

        @Bean
        public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
            final DataSourceInitializer initializer = new DataSourceInitializer();
            initializer.setDataSource(dataSource);
            initializer.setEnabled(Boolean.valueOf(env.getProperty("auth.web.security.datasourceinitializer.enable", "false")));
            initializer.setDatabasePopulator(databasePopulator());
            return initializer;
        }

        private DatabasePopulator databasePopulator() {
            final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(schemaScript);
            populator.addScript(dataScript);
            return populator;
        }

    }


}

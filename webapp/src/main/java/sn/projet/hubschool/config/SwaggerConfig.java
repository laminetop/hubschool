package sn.projet.hubschool.config;


import com.google.common.collect.Ordering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationCodeGrant;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.ClientCredentialsGrant;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ImplicitGrant;
import springfox.documentation.service.LoginEndpoint;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.Operation;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@PropertySource("classpath:/swagger.properties")
@ConditionalOnResource(resources = {"classpath:swagger.properties"})
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
@ComponentScan("sn.projet.hubschool.component")
class SwaggerConfig {

    public static final String securitySchemaOAuth2 = "oauth2";
    public static final String securitySchemaBasic = "basic";
    @Autowired
    private Environment env;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                //.groupName("hubschool-api")
                .apiInfo(apiInfo())
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .operationOrdering(new Ordering<Operation>() {
                    @Override
                    public int compare(Operation left, Operation right) {
                        return Integer.compare(left.getPosition(), right.getPosition());
                    }
                })
                .useDefaultResponseMessages(false)
//                .ignoredParameterTypes(OAuth2Authentication.class, Principal.class)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(500)
                                        .message("500 message")
                                        .responseModel(new ModelRef("Error"))
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("Forbidden!")
                                        .build()))
                .select()
                .apis(RequestHandlerSelectors.any())
                //.apis(RequestHandlerSelectors.basePackage("sn.projet.hubschool.controller"))
                //.paths(PathSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .securitySchemes(newArrayList(oAuth2SecuritySchema(), basicAuthSecuritySchema()))
                .securityContexts(newArrayList(securityContext()))
//                .enableUrlTemplating(false)
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("hubschool API")
                .description("hubschool API")
                .version(env.getProperty("api.version"))
                .termsOfServiceUrl("http://www.hubschool.com/")
                .contact(new Contact("Lamine Top", "", "toplamzo10@gmail.com"))
                .license("HubSchool Group")
                .licenseUrl("http://www.hubschool.com/")
                .build();
    }

    private OAuth oAuth2SecuritySchema() {

        List<AuthorizationScope> scopes = newArrayList(
                new AuthorizationScope("read", "Read scope"),
                new AuthorizationScope("write", "Write scope"),
                new AuthorizationScope("admin", "Admin scope"));

        LoginEndpoint loginEndpoint = new LoginEndpoint("/hubschool/oauth/token");
        String tokenUrl = "/hubschool/oauth/token";
        String authorizationUrl = "/hubschool/oauth/authorize";
        String tokenName = "access_token";
        TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint(authorizationUrl, "trusted", "secret");
        TokenEndpoint tokenEndpoint = new TokenEndpoint(tokenUrl, tokenName);

        GrantType authCodeGrantType = new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint);
        GrantType clientCredGrantType = new ClientCredentialsGrant(tokenUrl);
        GrantType implicitCredGrantType = new ImplicitGrant(loginEndpoint, tokenName);
        GrantType passwordGrantType = new ResourceOwnerPasswordCredentialsGrant(tokenUrl);


        return new OAuth(securitySchemaOAuth2,
                scopes,
                newArrayList(passwordGrantType,
//                        authCodeGrantType,
                        clientCredGrantType//,
//                        implicitCredGrantType
                )
        );
    }

    private BasicAuth basicAuthSecuritySchema() {
        return new BasicAuth(securitySchemaBasic);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api.*"))
                //.forPaths(PathSelectors.regex("/anyPath.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {

        AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "Read scope");
        authorizationScopes[1] = new AuthorizationScope("write", "Write scope");
        authorizationScopes[2] = new AuthorizationScope("admin", "Admin scope");
        return newArrayList(new SecurityReference(securitySchemaOAuth2, authorizationScopes),new SecurityReference(securitySchemaBasic, authorizationScopes));
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(
                "trusted",
                "secret",
                "secret-realm",
                "hubschool-app",
                "Bearer",
                ApiKeyVehicle.HEADER,
                "Authorization",
                "," /*scope separator*/);
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                null,// url
                "none",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                true,        // enableJsonEditor      => true | false
                true,         // showRequestHeaders    => true | false
                60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
    }
}

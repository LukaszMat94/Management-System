package org.matusikl.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@Import({org.springdoc.core.SpringDocConfiguration.class,
        org.springdoc.webmvc.core.SpringDocWebMvcConfiguration.class,
        org.springdoc.webmvc.ui.SwaggerConfig.class,
        org.springdoc.core.SwaggerUiConfigProperties.class,
        org.springdoc.core.SwaggerUiOAuthProperties.class,
        org.springdoc.core.SpringDocConfigProperties.class,
        org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class})
@ComponentScan(basePackages = {"org.matusikl.controller", "org.matusikl.controlleradvice", "org.springdoc"})
@PropertySource("classpath:/application.properties")
@SecuritySchemes({
        @SecurityScheme(
                name = "bearerAuth",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "bearer"
        ),
        @SecurityScheme(
                name = "refreshAuth",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "bearer"
        )}
)
public class CustomWebMvcConfig implements WebMvcConfigurer{

    @Override
    public Validator getValidator(){
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }

    @Bean
    public SwaggerUiConfigProperties swaggerUiConfigProperties(){
        SwaggerUiConfigProperties properties = new SwaggerUiConfigProperties();
        properties.setFilter("true");
        properties.setDisableSwaggerDefaultUrl(true);
        properties.setTagsSorter("alpha");
        properties.setOperationsSorter("alpha");
        return properties;
    }
}

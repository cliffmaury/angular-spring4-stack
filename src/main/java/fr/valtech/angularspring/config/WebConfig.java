package fr.valtech.angularspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by cliff.maury on 22/10/2014.
 */

/**
 * Configuration for Spring MVC
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = fr.valtech.angularspring.app.web.__Package.class)
@Import(LogConfig.class)
public class WebConfig extends WebMvcConfigurerAdapter {


    // see http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-beanvalidation-spring

    // Spring provides full support for the Bean Validation API.
    // This includes convenient support for bootstrapping a JSR-303/JSR-349 Bean Validation provider as a Spring bean.
    // This allows for a javax.validation.ValidatorFactory or javax.validation.Validator to be injected wherever validation is needed in your application.
//    @Bean
//    public LocalValidatorFactoryBean validator() {
//        return new LocalValidatorFactoryBean();
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/styles/**").addResourceLocations("/styles/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
        registry.addResourceHandler("/views/**").addResourceLocations("/views/");
        registry.addResourceHandler("/*").addResourceLocations("/*");


    }
}

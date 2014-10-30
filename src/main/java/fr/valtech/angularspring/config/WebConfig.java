package fr.valtech.angularspring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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
public class WebConfig extends WebMvcConfigurerAdapter {


}

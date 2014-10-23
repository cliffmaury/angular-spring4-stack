package fr.valtech.angularspring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by cliff.maury on 22/10/2014.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = fr.valtech.angularspring.app.rest.__Package.class)
public class WebMvcConfig extends WebMvcConfigurerAdapter {


}

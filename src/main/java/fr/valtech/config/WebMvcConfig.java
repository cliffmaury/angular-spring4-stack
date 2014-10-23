package fr.valtech.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by cliff.maury on 22/10/2014.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = fr.valtech.app.rest.__Package.class)
public class WebMvcConfig extends WebMvcConfigurerAdapter {


}

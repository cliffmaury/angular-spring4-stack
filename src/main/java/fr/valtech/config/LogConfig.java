package fr.valtech.config;

import fr.valtech.log.Log;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cliff.maury on 22/10/2014.
 */
@Configuration
@ComponentScan(basePackageClasses = Log.class)
public class LogConfig {
}

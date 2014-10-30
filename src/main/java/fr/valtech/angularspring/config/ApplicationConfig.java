package fr.valtech.angularspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by cliff.maury on 30/10/2014.
 */

/**
 * Configuration for persistence, infrastructure, service and security layers
 */
@Configuration
@Import({
        LogConfig.class,
        PersistenceJPAConfig.class,
        AppConfig.class
})
public class ApplicationConfig {
}

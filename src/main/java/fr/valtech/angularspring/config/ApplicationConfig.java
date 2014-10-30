package fr.valtech.angularspring.config;

import fr.valtech.angularspring.log.Log;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Arrays;

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

    @Log
    private static Logger logger;

    @Inject
    private Environment environment;

    /**
     * Application initialization
     * <p>
     * Spring profiles are set by -Dspring.profiles.active=PROFILE
     */
    @PostConstruct
    public void initApp() {
        logger.debug("Looking for Spring profiles");

        if (environment.getActiveProfiles().length == 0) {
            logger.info("No Spring profile set, running with default configuration.");
        } else {
            logger.info("Detected Spring profiles : {}", Arrays.toString(environment.getActiveProfiles()));
        }
    }

}

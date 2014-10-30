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
 * Configuration for datasource, persistence, repository, service and security layers
 * <p>
 * Inspired by http://javaetmoi.com/2014/06/spring-framework-java-configuration/
 */
@Configuration
@Import({
        LogConfig.class,
        DataSourceConfig.class,
        PersistenceConfig.class,
        DaoConfig.class,
        RepositoryConfig.class,
        ServiceConfig.class
})
public class ApplicationConfig {

    @Log
    private static Logger logger;

    @Inject
    private Environment environment;

    /**
     * Application initialization
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

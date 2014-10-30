package fr.valtech.angularspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by cliff.maury on 30/10/2014.
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = fr.valtech.angularspring.app.repository.__Package.class)
public class RepositoryConfig {
}

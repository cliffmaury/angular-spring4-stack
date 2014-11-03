package fr.valtech.angularspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Created by cliff.maury on 03/11/2014.
 */

/**
 * Sometimes you may need to perform operations that are more complicated
 * than are possible with the @EnableGlobalMethodSecurity annotation allow.
 * For these instances, you can extend the GlobalMethodSecurityConfiguration
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class MethodSecurityConfig {
}

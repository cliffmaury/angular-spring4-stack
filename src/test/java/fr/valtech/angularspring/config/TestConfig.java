package fr.valtech.angularspring.config;

import fr.valtech.angularspring.app.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cliff.maury on 22/10/2014.
 */
@Configuration
public class TestConfig {

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

}

package fr.valtech.angularspring.config;

import fr.valtech.angularspring.app.repository.UserRepository;
import fr.valtech.angularspring.app.service.UserService;
import fr.valtech.angularspring.app.service.impl.UserServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cliff.maury on 22/10/2014.
 */
@Configuration
public class TestConfig {

    @Bean
    public UserService userServiceMock() {
        return Mockito.mock(UserServiceImpl.class);
    }

    @Bean
    public UserRepository userRepositoryMock() {
        return Mockito.mock(UserRepository.class);
    }

}

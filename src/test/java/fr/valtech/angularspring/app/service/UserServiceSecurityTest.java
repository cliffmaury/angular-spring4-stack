package fr.valtech.angularspring.app.service;

import fr.valtech.angularspring.app.domain.User;
import fr.valtech.angularspring.app.repository.UserRepository;
import fr.valtech.angularspring.app.web.users.fixture.RestDataFixture;
import fr.valtech.angularspring.config.LogConfig;
import fr.valtech.angularspring.config.SecurityConfig;
import fr.valtech.angularspring.config.ServiceConfig;
import fr.valtech.angularspring.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


/**
 * Created by cliff.maury on 23/10/2014.
 */

/**
 * see http://spring.io/blog/2014/05/07/preview-spring-security-test-method-security
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LogConfig.class, ServiceConfig.class, TestConfig.class, SecurityConfig.class })
@WebAppConfiguration
@TestExecutionListeners(listeners = { ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class })
public class UserServiceSecurityTest {

    @Inject
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Inject
    @Qualifier("userRepositoryMock")
    private UserRepository userRepositoryMock;

    @Before
    public void setup() {
        Mockito.reset(userRepositoryMock);
    }

    @Test
    public void findAll() {

        when(userRepositoryMock.findAll()).thenReturn(RestDataFixture.findAllUsers());

        userService.findAllUsers();
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void createUserWithAnonymous() {
        userService.createUser("", "");
    }

    @Test
    @WithMockUser // user/password
    public void findAllWithMockUser() {
        userService.findAllUsers();
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser // user/password
    public void createUserWithMockUser() {
        userService.createUser("", "");
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void createUserWithMockAdmin() {

        String name = "name";
        String lastName = "lastName";
        when(userRepositoryMock.save(Matchers.any(User.class))).thenReturn(new User(name, lastName));

        User user = userService.createUser(name, lastName);
        assertThat(user.getName(), is(name));
        assertThat(user.getLastName(), is(lastName));
    }

    // we can also use @WithUserDetails or @WithSecurityContext
}
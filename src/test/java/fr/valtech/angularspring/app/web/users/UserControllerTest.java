package fr.valtech.angularspring.app.web.users;

import fr.valtech.angularspring.app.service.UserService;
import fr.valtech.angularspring.app.web.users.fixture.RestDataFixture;
import fr.valtech.angularspring.app.web.utils.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by cliff.maury on 23/10/2014.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    // see tutorial
    // http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-configuration#appcontext-config

    // spring doc
    // http://docs.spring.io/spring/docs/current/spring-framework-reference/html/testing.html#spring-mvc-test-framework

    // The "standaloneSetup" on the other hand is a little closer to a unit test.
    // It tests one controller at a time, the controller can be injected with mock dependencies manually, and it doesn’t involve loading Spring configuration.
    // Such tests are more focused in style and make it easier to see which controller is being tested,
    // whether any specific Spring MVC configuration is required to work, and so on.
    // The "standaloneSetup" is also a very convenient way to write ad-hoc tests to verify some behavior or to debug an issue.

    private MockMvc mockMvc;

    @Mock
    private UserService userServiceMock;

    private UserController userController;

    @Before
    public void setup() {
        userController = new UserController(userServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        // Note that the expectation is always applied and cannot be overridden without creating a separate MockMvc instance.
        //mockMvc = MockMvcBuilders.standaloneSetup(userController).alwaysExpect(status().isOk())
        //       .alwaysExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8)).build();

    }

    @Test
    public void findAllUsers_should_return_3_users() throws Exception {

        when(userServiceMock.findAllUsers()).thenReturn(RestDataFixture.findAllUsers());
        mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8)).
                andExpect(jsonPath("$", hasSize(3)));

        verify(userServiceMock, times(1)).findAllUsers();
        verifyNoMoreInteractions(userServiceMock);
    }

}

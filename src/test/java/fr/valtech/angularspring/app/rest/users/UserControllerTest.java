package fr.valtech.angularspring.app.rest.users;

import fr.valtech.angularspring.app.rest.users.fixture.RestDataFixture;
import fr.valtech.angularspring.app.rest.utils.TestUtil;
import fr.valtech.angularspring.app.service.UserService;
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

    private MockMvc mockMvc;

    @Mock
    private UserService userServiceMock;

    private UserController userController;

    @Before
    public void setup() {
        userController = new UserController(userServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void findAllUsers_should_return_3_users() throws Exception {

        when(userServiceMock.findAllUsers()).thenReturn(RestDataFixture.findAllUsers());
        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8)).
                andExpect(jsonPath("$", hasSize(3)));

        verify(userServiceMock, times(1)).findAllUsers();
        verifyNoMoreInteractions(userServiceMock);
    }

}

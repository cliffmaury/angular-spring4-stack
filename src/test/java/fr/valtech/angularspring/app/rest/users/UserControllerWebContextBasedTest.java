package fr.valtech.angularspring.app.rest.users;

import fr.valtech.angularspring.app.rest.users.fixture.RestDataFixture;
import fr.valtech.angularspring.app.rest.utils.TestUtil;
import fr.valtech.angularspring.app.service.UserService;
import fr.valtech.angularspring.config.TestConfig;
import fr.valtech.angularspring.config.WebMvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by cliff.maury on 23/10/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebMvcConfig.class,})
@WebAppConfiguration
public class UserControllerWebContextBasedTest {

    // see tutorial
    // http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-configuration#appcontext-config

    private MockMvc mockMvc;

    @Inject
    private UserService userServiceMock;

    @Inject
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(userServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
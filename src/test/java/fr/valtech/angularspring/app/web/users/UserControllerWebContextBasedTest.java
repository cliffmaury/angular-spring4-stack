package fr.valtech.angularspring.app.web.users;

import fr.valtech.angularspring.app.domain.User;
import fr.valtech.angularspring.app.service.UserService;
import fr.valtech.angularspring.app.web.users.fixture.RestDataFixture;
import fr.valtech.angularspring.app.web.utils.TestUtil;
import fr.valtech.angularspring.app.web.view.UserView;
import fr.valtech.angularspring.config.TestConfig;
import fr.valtech.angularspring.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by cliff.maury on 23/10/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class, WebConfig.class, })
@WebAppConfiguration
public class UserControllerWebContextBasedTest {

    // see tutorial
    // http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-configuration#appcontext-config

    // spring doc
    // http://docs.spring.io/spring/docs/current/spring-framework-reference/html/testing.html#spring-mvc-test-framework

    // The "webAppContextSetup" loads the actual Spring MVC configuration resulting in a more complete integration test.
    // Since the TestContext framework caches the loaded Spring configuration, it helps to keep tests running fast even as more tests get added.
    // Furthermore, you can inject mock services into controllers through Spring configuration, in order to remain focused on testing the web layer.
    // Here is an example of declaring a mock service with Mockito:

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

        mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andDo(print()).
                andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8)).
                andExpect(jsonPath("$", hasSize(3)));

        verify(userServiceMock, times(1)).findAllUsers();
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void createUser_with_name_and_firstName_too_long() throws Exception {

        UserView userView = new UserView(TestUtil.createStringWithLength(51), TestUtil.createStringWithLength(51));
        mockPost("/api/users", userView, status().isBadRequest());
    }

    @Test
    public void createUser_with_name_and_firstName_NULL() throws Exception {

        UserView userView = new UserView(null, null);
        mockPost("/api/users", userView, status().isBadRequest());
    }

    @Test
    public void createUser_with_name_and_firstName_Is_Created() throws Exception {

        when(userServiceMock.createUser(anyString(), anyString())).thenReturn(new User("a", "b"));

        UserView userView = new UserView(TestUtil.createStringWithLength(30), TestUtil.createStringWithLength(20));
        mockPost("/api/users", userView, status().isCreated());
    }

    private void mockPost(String url, Object content, ResultMatcher status) throws Exception {
        mockMvc.perform(post(url).contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonString(content))).andDo(print()).andExpect(status);
    }
}
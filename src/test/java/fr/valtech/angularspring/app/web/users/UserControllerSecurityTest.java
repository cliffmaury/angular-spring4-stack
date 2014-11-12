package fr.valtech.angularspring.app.web.users;

import fr.valtech.angularspring.app.domain.User;
import fr.valtech.angularspring.app.service.UserService;
import fr.valtech.angularspring.app.web.users.fixture.RestDataFixture;
import fr.valtech.angularspring.app.web.utils.TestUtil;
import fr.valtech.angularspring.app.web.view.UserView;
import fr.valtech.angularspring.config.SecurityConfig;
import fr.valtech.angularspring.config.TestConfig;
import fr.valtech.angularspring.config.WebConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.Filter;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by cliff.maury on 23/10/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class, WebConfig.class, SecurityConfig.class })
@WebAppConfiguration
@TestExecutionListeners(listeners = { ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class })
public class UserControllerSecurityTest {

    // see doc
    // http://spring.io/blog/2014/05/23/preview-spring-security-test-web-security

    private MockMvc mockMvc;

    @Inject
    private UserService userServiceMock;

    @Inject
    private Filter springSecurityFilterChain;

    @Inject
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(userServiceMock);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .defaultRequest(get("/").with(testSecurityContext()))
                .build();
    }

    @Test
    public void findAllUsersWhenNoLoggedIn() throws Exception {

        // to enable CSRF
        // mockMvc.perform(get("/users").with(csrf())

        when(userServiceMock.findAllUsers()).thenReturn(RestDataFixture.findAllUsers());

        mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    @WithMockUser
    public void findAllUsersWithUser() throws Exception {

        when(userServiceMock.findAllUsers()).thenReturn(RestDataFixture.findAllUsers());

        mockMvc
                .perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        ;
//        verify(userServiceMock, times(1)).findAllUsers();
//        verifyNoMoreInteractions(userServiceMock);

    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser
    @Ignore
    public void createUserWithUser() throws Exception {

        when(userServiceMock.createUser(anyString(), anyString())).thenReturn(new User("a", "b"));

        mockMvc
                .perform(post("/api/users").accept(MediaType.APPLICATION_JSON));

//        verify(userServiceMock, times(0)).createUser(anyString(), anyString());
//        verifyNoMoreInteractions(userServiceMock);

    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void createUserWithAdmin() throws Exception {

        when(userServiceMock.createUser(anyString(), anyString())).thenReturn(new User("a", "b"));

        mockMvc
                .perform(post("/api/users").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonString(new UserView("aa", "bb")))
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated())
        ;

//        verify(userServiceMock).createUser(name,lastName);
//        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void createUser_with_name_and_firstName_Is_Created() throws Exception {

        when(userServiceMock.createUser(anyString(), anyString())).thenReturn(new User("a", "b"));

        UserView userView = new UserView(TestUtil.createStringWithLength(30), TestUtil.createStringWithLength(20));
        mockPost("/api/users", userView, status().isCreated());
    }

    private void mockPost(String url, Object content, ResultMatcher status) throws Exception {
        mockMvc.perform(post(url).contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonString(content))).andDo(print()).andExpect(status);
    }
}
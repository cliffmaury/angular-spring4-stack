package fr.valtech.angularspring.app.web.users;

import fr.valtech.angularspring.app.web.users.fixture.RestDataFixture;
import fr.valtech.angularspring.app.web.utils.TestUtil;
import fr.valtech.angularspring.app.web.view.UserView;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by cliff.maury on 27/10/2014.
 */
public class UsersControllerClientTest {

    private RestTemplate restTemplate;
    private MockRestServiceServer server;

    @Before
    public void setup() {

        this.restTemplate = new RestTemplate();
        this.server = MockRestServiceServer.createServer(this.restTemplate);
    }

    @Test
    public void getUsers() throws IOException {

        this.server.expect(requestTo("/users")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(TestUtil.convertObjectToJsonString(RestDataFixture.findAllUsers()), TestUtil.APPLICATION_JSON_UTF8));

        List<UserView> users = restTemplate.getForObject("/users", List.class);

        assertThat(users.size(), is(3));

        this.server.verify();
    }

}

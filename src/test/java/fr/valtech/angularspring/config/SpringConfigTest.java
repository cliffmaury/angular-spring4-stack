package fr.valtech.angularspring.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

/**
 * Created by cliff.maury on 30/10/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = ApplicationConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@ActiveProfiles(Profiles.TEST)
@WebAppConfiguration
public class SpringConfigTest {

    @Inject
    private WebApplicationContext webApplicationContext;

    @Test
    public void checkSpringConfig() {
        Assert.assertNotNull(webApplicationContext);
    }

}

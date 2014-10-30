package fr.valtech.angularspring.app.repository;

import fr.valtech.angularspring.app.domain.User;
import fr.valtech.angularspring.config.LogConfig;
import fr.valtech.angularspring.config.PersistenceJPAConfig;
import fr.valtech.angularspring.log.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by cliff.maury on 28/10/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LogConfig.class, PersistenceJPAConfig.class})
@Transactional
public class UserRepositoryTest {

    @Log
    private Logger logger;

    @Inject
    private UserRepository userRepository;

    @Before
    public void setup() {

        userRepository.save(new User("MYNAME", "LASTNAME"));
        userRepository.save(new User("NAME2", "LASTNAME2"));
        userRepository.save(new User("NAME3", "LASWTNAME3"));
    }

    @Test
    public void createUserJPA() {
        logger.debug("create User");
        User user = userRepository.save(new User("N", "L"));
        logger.debug("User created - id : " + user.getId());
        assertNotNull(user.getId());
    }

    @Test
    public void testFindAllJPA() {
        logger.debug("find All");
        List<User> all = userRepository.findAll();
        assertThat(all.size(), is(3));
    }

}

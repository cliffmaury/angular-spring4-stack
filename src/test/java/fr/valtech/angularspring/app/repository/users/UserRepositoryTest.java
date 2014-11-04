package fr.valtech.angularspring.app.repository.users;

import fr.valtech.angularspring.app.domain.User;
import fr.valtech.angularspring.app.repository.UserRepository;
import fr.valtech.angularspring.config.ApplicationConfig;
import fr.valtech.angularspring.config.Profiles;
import fr.valtech.angularspring.log.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ActiveProfiles;
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
@ContextConfiguration(classes = { ApplicationConfig.class })
// Annotating a test method with @Transactional causes the test to be run within a transaction that will,
// by default, be automatically rolled back after completion of the test.
@Transactional
//@TransactionConfiguration(defaultRollback = false)
@ActiveProfiles(Profiles.TEST)
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

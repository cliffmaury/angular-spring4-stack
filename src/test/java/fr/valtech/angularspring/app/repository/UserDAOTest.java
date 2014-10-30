package fr.valtech.angularspring.app.repository;

import fr.valtech.angularspring.app.dao.UserDAO;
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
public class UserDAOTest {

    @Log
    private Logger logger;

    @Inject
    private UserDAO userDAO;

    @Before
    public void setup() {

        userDAO.create(new User("MYNAME", "LASTNAME"));
        userDAO.create(new User("NAME2", "LASTNAME2"));
        userDAO.create(new User("NAME3", "LASWTNAME3"));
    }

    @Test
    public void createUser() {
        logger.debug("create User");
        Long id = userDAO.create(new User("N", "L"));
        logger.debug("User created - id : " + id);
        assertNotNull(id);
    }

    @Test
    public void testFindAll() {
        logger.debug("find All");
        List<User> all = userDAO.findAll();
        assertThat(all.size(), is(3));
    }

}

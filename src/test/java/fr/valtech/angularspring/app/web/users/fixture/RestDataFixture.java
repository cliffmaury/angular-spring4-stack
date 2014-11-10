package fr.valtech.angularspring.app.web.users.fixture;

import fr.valtech.angularspring.app.domain.User;
import fr.valtech.angularspring.app.web.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cliff.maury on 23/10/2014.
 */
public class RestDataFixture {

    // see spring.io doc http://spring.io/guides/tutorials/rest/2/

    public static List<User> findAllUsers() {
        List<User> users = new ArrayList<>();

        users.add(createRandomUser());
        users.add(createRandomUser());
        users.add(createRandomUser());

        return users;
    }

    public static User createRandomUser() {

        return new User("name:" + TestUtil.createString(), "lastName:" + TestUtil.createString());
    }
}

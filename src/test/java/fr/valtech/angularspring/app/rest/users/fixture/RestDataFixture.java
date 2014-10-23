package fr.valtech.angularspring.app.rest.users.fixture;

import fr.valtech.angularspring.app.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cliff.maury on 23/10/2014.
 */
public class RestDataFixture {

    // see spring.io doc http://spring.io/guides/tutorials/rest/2/

    private final static String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Random random = new Random();

    public static List<User> findAllUsers() {
        List<User> users = new ArrayList<>();

        users.add(createRandomUser());
        users.add(createRandomUser());
        users.add(createRandomUser());

        return users;
    }

    public static User createRandomUser() {

        return new User("name:" + randomString(), "lastName:" + randomString());
    }

    private static String randomString() {

        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            String oneChar = ALPHA.charAt(random.nextInt(ALPHA.length())) + "";
            sb.append(random.nextBoolean() ? oneChar.toLowerCase() : oneChar);
        }
        return sb.toString();
    }


}

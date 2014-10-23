package fr.valtech.app.service;

import fr.valtech.app.domain.User;
import fr.valtech.log.Log;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by cliff.maury on 23/10/2014.
 */
@Service
public class UserService {

    @Log
    private Logger logger;

    public void createUser(String name, String lastName) {
        logger.warn("create User");
    }

    public List<User> findAllUsers() {
        throw new NotImplementedException();
    }
}

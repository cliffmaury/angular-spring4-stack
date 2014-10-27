package fr.valtech.angularspring.app.service.impl;

import fr.valtech.angularspring.app.domain.User;
import fr.valtech.angularspring.app.service.UserService;
import fr.valtech.angularspring.log.Log;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by cliff.maury on 23/10/2014.
 */
@Service
public class UserServiceImpl implements UserService {

    @Log
    private Logger logger;

    public void createUser(String name, String lastName) {
        logger.warn("create User");
    }

    public List<User> findAllUsers() {
        throw new NotImplementedException();
    }
}

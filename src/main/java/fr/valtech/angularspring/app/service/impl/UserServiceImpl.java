package fr.valtech.angularspring.app.service.impl;

import fr.valtech.angularspring.app.domain.User;
import fr.valtech.angularspring.app.repository.UserRepository;
import fr.valtech.angularspring.app.service.UserService;
import fr.valtech.angularspring.log.Log;
import fr.valtech.angularspring.security.Authorities;
import org.slf4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by cliff.maury on 23/10/2014.
 */
@Service
public class UserServiceImpl implements UserService {

    @Log
    private Logger logger;

    @Inject
    private UserRepository userRepository;

    @Secured(Authorities.ADMIN)
    public User createUser(String name, String lastName) {
        logger.warn("create User");
        return userRepository.save(new User(name, lastName));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}

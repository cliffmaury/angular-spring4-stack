package fr.valtech.angularspring.app.service;

import fr.valtech.angularspring.app.domain.User;

import java.util.List;

/**
 * Created by cliff.maury on 27/10/2014.
 */
public interface UserService {

    void createUser(String name, String lastName);

    List<User> findAllUsers();
}

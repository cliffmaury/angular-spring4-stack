package fr.valtech.app.rest.users;

import fr.valtech.app.domain.User;
import fr.valtech.app.rest.dto.UserDTO;
import fr.valtech.app.service.UserService;
import fr.valtech.log.Log;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cliff.maury on 22/10/2014.
 */
@RestController
public class UserController {

    @Log
    private Logger logger;

    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        logger.warn("enter controller");
        return "Hello World 3";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserDTO user() {
        UserDTO u = new UserDTO("NAME", "LASTNAME");
        return u;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public UserDTO createUser() {
        userService.createUser("NAME", "LASTNAME");
        return new UserDTO("A", "B");
    }

    @RequestMapping("/users")
    public List<UserDTO> users() {
        List<User> allUsers = userService.findAllUsers();
        List<UserDTO> users = new ArrayList<>();
        allUsers.stream().map(u -> new UserDTO(u.getName(), u.getLastName())).forEach(u -> users.add(u));
        return users;
    }
}

package fr.valtech.angularspring.app.web.users;

import fr.valtech.angularspring.app.domain.User;
import fr.valtech.angularspring.app.service.UserService;
import fr.valtech.angularspring.app.web.dto.UserDTO;
import fr.valtech.angularspring.log.Log;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
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
    public String hello(@AuthenticationPrincipal UserDetails userDetails) {
        logger.warn("enter controller");
        return "Hello " + userDetails.getUsername() + "!";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserDTO user() {
        UserDTO u = new UserDTO("NAME", "LASTNAME");
        return u;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public UserDTO createUser() {
        User user = userService.createUser("NAME", "LASTNAME");
        return new UserDTO(user.getName(), user.getLastName());
    }

    @RequestMapping("/users")
    public List<UserDTO> users() {
        List<User> allUsers = userService.findAllUsers();
        List<UserDTO> users = new ArrayList<>();
        allUsers.stream().map(u -> new UserDTO(u.getName(), u.getLastName())).forEach(u -> users.add(u));
        return users;
    }
}

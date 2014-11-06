package fr.valtech.angularspring.app.web.users;

import fr.valtech.angularspring.app.domain.User;
import fr.valtech.angularspring.app.service.UserService;
import fr.valtech.angularspring.app.web.view.UserView;
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
@RequestMapping("/api")
public class UserController {

    @Log
    private Logger logger;

    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String hello(@AuthenticationPrincipal UserDetails userDetails) {
        logger.warn("enter controller");
        return "Hello " + userDetails.getUsername() + "!";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserView user() {
        UserView u = new UserView("NAME", "LASTNAME");
        return u;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public UserView createUser() {
        User user = userService.createUser("NAME", "LASTNAME");
        return new UserView(user.getName(), user.getLastName());
    }

    @RequestMapping("/users")
    public List<UserView> users() {
        List<User> allUsers = userService.findAllUsers();
        List<UserView> users = new ArrayList<>();
        allUsers.stream().map(u -> new UserView(u.getName(), u.getLastName())).forEach(u -> users.add(u));
        return users;
    }
}

package fr.valtech.app.rest.users;

import fr.valtech.app.rest.dto.UserDTO;
import fr.valtech.app.service.UserService;
import fr.valtech.log.Log;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by cliff.maury on 22/10/2014.
 */
@RestController
public class UserController {

    @Log
    private Logger logger;

    @Inject
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        logger.warn("enter controller");
        return "Hello World 3";
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public UserDTO user() {
        UserDTO u = new UserDTO("NAME","LASTNAME");
        return  u;
    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public UserDTO createUser() {
        userService.createUser("NAME","LASTNAME");
        return new UserDTO("A","B");
    }
}

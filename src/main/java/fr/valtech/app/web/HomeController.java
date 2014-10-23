package fr.valtech.app.web;

import fr.valtech.log.Log;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cliff.maury on 22/10/2014.
 */
@RestController
public class HomeController {

    @Log
    private Logger logger;

    public static class User {
        String name, lastName;

        public String getLastName() {
            return lastName;
        }

        public String getName() {
            return name;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        logger.warn("enter controller");
        return "Hello World 3";
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public User user() {
        User u = new User();
        u.name = "NAME";
        u.lastName = "LASTNAME";
        return  u;
    }
}

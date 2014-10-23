package fr.valtech.app.rest;

import fr.valtech.app.rest.dto.UserDTO;
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
}

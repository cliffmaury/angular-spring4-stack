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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        logger.debug("enter controller");
        return "Hello World 3";
    }
}

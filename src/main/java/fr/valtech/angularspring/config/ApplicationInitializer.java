package fr.valtech.angularspring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by cliff.maury on 22/10/2014.
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ ApplicationConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // initialize default profile
        if (System.getProperty("spring.profiles.active") == null) {
            System.setProperty("spring.profiles.active", Profiles.TEST);
        }
        super.onStartup(servletContext);
    }
}
package fr.valtech.angularspring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

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
        return new Class<?>[]{ LogConfig.class, WebMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }

}
package fr.valtech.angularspring.config;

import fr.valtech.angularspring.security.Authorities;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import javax.inject.Inject;

/**
 * Created by cliff.maury on 03/11/2014.
 */

/**
 * http://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#jc-httpsecurity
 * <p>
 * Warning! With Java Config Defaults URLs and parameters :
 * <p>
 * GET /login renders the login page instead of /spring_security_login
 * <p>
 * POST /login authenticates the user instead of /j_spring_security_check
 * <p>
 * The username parameter defaults to username instead of j_username
 * <p>
 * The password parameter defaults to password instead of j_password
 */
@Configuration
@EnableWebMvcSecurity
@Import(MethodSecurityConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").authorities(Authorities.USER).and()
                .withUser("admin").password("admin").authorities(Authorities.ADMIN);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable();
    }
}

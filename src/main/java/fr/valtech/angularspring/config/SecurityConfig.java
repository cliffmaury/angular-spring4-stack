package fr.valtech.angularspring.config;

import fr.valtech.angularspring.security.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
@ComponentScan(basePackageClasses = fr.valtech.angularspring.security.__Package.class)
public class SecurityConfig {

    // USE MUTIPLE HTTP SECURITY
    // see http://docs.spring.io/spring-security/site/docs/4.0.0.M2/reference/htmlsingle/#multiple-httpsecurity

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").authorities(Authorities.USER).and()
                .withUser("admin").password("admin").authorities(Authorities.ADMIN);
    }

    @Configuration
    public static class AppSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            super.configure(http);
            http.csrf().disable();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers("/bower_components/**")
                    .antMatchers("/fonts/**")
                    .antMatchers("/images/**")
                    .antMatchers("/scripts/**")
                    .antMatchers("/styles/**")
                    .antMatchers("/views/**")
                    ;
        }
    }

    @Configuration
    @Order(1)
    public static class RestSecurityConfig extends WebSecurityConfigurerAdapter {

        @Inject
        private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

        @Inject
        private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

        @Inject
        private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

        @Inject
        private RestLogoutSuccessHandler restLogoutSuccessHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .anyRequest().authenticated().and()
                    .exceptionHandling().
                    authenticationEntryPoint(restAuthenticationEntryPoint).and()
                    .formLogin()
                    .loginProcessingUrl("/api/login")
                    .successHandler(restAuthenticationSuccessHandler)
                    .failureHandler(restAuthenticationFailureHandler).and()
                    .logout().logoutUrl("/api/logout").logoutSuccessHandler(restLogoutSuccessHandler).and()
                    .httpBasic();
        }
    }
}

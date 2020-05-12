package com.yicj.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService ;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository ;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("login.html").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .successHandler( (request, response,  authentication) ->{
                response.setContentType("application/json;charset=utf-8");
                //{"code":"200","msg"："success"}
                response.getWriter().write("{\"code\":\"200\",\"msg\"：\"success\"}");
            })
            .permitAll()
            .and()
        .rememberMe()
            .userDetailsService(userDetailsService)
            .tokenRepository(persistentTokenRepository)
            .tokenValiditySeconds(36000)
            //.key("blurooo")
            .and()
        .csrf().disable();
    }

}

package com.yicj.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService ;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("login.html").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login.html")
            .loginProcessingUrl("/login")
            .permitAll()
            .and()
//        .rememberMe()
//            .userDetailsService(userDetailsService)
//            .and()
        .csrf().disable();
    }

}

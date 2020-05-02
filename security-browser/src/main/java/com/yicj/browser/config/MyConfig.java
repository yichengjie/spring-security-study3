package com.yicj.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import javax.sql.DataSource;


@Configuration
public class MyConfig {

    @Autowired
    private DataSource dataSource ;

    @Bean
    public UserDetailsService userDetailsService(){
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager() ;
        manager.setDataSource(dataSource);
        if (!manager.userExists("user")){
            manager.createUser(User.withUsername("user").password("123").roles("USER").build());
        }
        if (!manager.userExists("admin")){
            manager.createUser(User.withUsername("admin").password("123").roles("USER","ADMIN").build());
        }
        return manager ;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance() ;
    }
}

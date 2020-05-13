package com.yicj.study.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Profile("simple")
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user")
                .password(passwordEncoder().encode("user")).roles("USER").and()
            .withUser("admin")
                .password(passwordEncoder().encode("admin")).roles("ADMIN") ;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("SecurityConfiguration中配置HttpSecurity对象执行");
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().hasAnyRole("USER","ADMIN")
                .and()
            .formLogin() ;
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/favicon.ico") ;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder() ;
    }
}

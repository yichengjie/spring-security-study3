package com.yicj.config;

import com.yicj.vercode.MyAuthenticationFailureHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
            .antMatchers("/admin/api/**").hasRole("ADMIN")
            .antMatchers("/user/api/**").hasRole("USER")
            .antMatchers("/app/api/**","/captcha.jpg","/favicon.ico").permitAll()
            .anyRequest().authenticated()
            .and()
        .csrf().disable()
        .httpBasic()
            .and()
        .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/auth/form")
            .permitAll()
            .failureHandler(new MyAuthenticationFailureHandler()) ;

    }

}

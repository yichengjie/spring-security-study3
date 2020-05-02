package com.yicj.core.config;

import com.yicj.core.vercode.MyAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails>  myAuthenticationDetailsSource ;

    @Autowired
    private AuthenticationProvider authenticationProvider ;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
            .antMatchers("/admin/api/**").hasRole("ADMIN")
            .antMatchers("/user/api/**").hasRole("USER")
            .antMatchers("/app/api/**","/captcha.jpg","/favicon.ico","/loginPage").permitAll()
            .anyRequest().authenticated()
            .and()
        .csrf().disable()
//        .httpBasic()
//            .and()
        .formLogin()
            //应用AuthenticationDetailsSource
            .authenticationDetailsSource(myAuthenticationDetailsSource)
            // 需要身份认证时跳转的地址
            .loginPage("/authentication/require")
            .loginProcessingUrl("/auth/form")
            .permitAll()
            .failureHandler(new MyAuthenticationFailureHandler())
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider) ;
    }
}

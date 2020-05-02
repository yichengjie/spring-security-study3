package com.yicj.browser.config;

import com.yicj.browser.handler.MyAuthenticationFailureHandler;
import com.yicj.browser.handler.MyAuthenticationSuccessHandler;
import com.yicj.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties ;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler ;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler ;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
                .antMatchers("/admin/api/**").hasRole("ADMIN")
                .antMatchers("/user/api/**").hasRole("USER")
                .antMatchers("/app/api/**","/favicon.ico",securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                // 需要身份认证时跳转的地址
                .loginPage("/authentication/require")
                .loginProcessingUrl("/auth/form")
                .permitAll()
                .failureHandler(myAuthenticationFailureHandler)
        ;
    }
}

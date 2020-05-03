package com.yicj.core.authentication;

import com.yicj.core.handler.MyAuthenticationFailureHandler;
import com.yicj.core.handler.MyAuthenticationSuccessHandler;
import com.yicj.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler ;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler ;


    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception{
            http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .permitAll() ;
    }

}

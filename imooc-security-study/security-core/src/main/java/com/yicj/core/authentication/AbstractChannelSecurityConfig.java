package com.yicj.core.authentication;

import com.yicj.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Deprecated
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler ;
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler ;


    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception{
        http.formLogin()
            .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
            .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
            .successHandler(myAuthenticationSuccessHandler)
            .failureHandler(myAuthenticationFailureHandler)
        ;
    }

}

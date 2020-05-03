package com.yicj.browser.config;

import com.yicj.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yicj.core.handler.MyAuthenticationFailureHandler;
import com.yicj.core.handler.MyAuthenticationSuccessHandler;
import com.yicj.core.properties.SecurityConstants;
import com.yicj.core.properties.SecurityProperties;
import com.yicj.core.validate.code.SmsCodeFilter;
import com.yicj.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler ;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler ;

    @Autowired
    private SecurityProperties securityProperties ;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig ;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter() ;
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();


        SmsCodeFilter smsCodeFilter = new SmsCodeFilter() ;
        smsCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();


        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .permitAll()
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
            .authorizeRequests()
                // 登录页面，和验证码页面不需要权限验证
                .antMatchers(securityProperties.getBrowser().getLoginPage(),
                        "/code/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf().disable()
            //短信验证相关配置
            .apply(smsCodeAuthenticationSecurityConfig)
        ;

    }
}

package com.yicj.core.authentication.mobile;


import com.yicj.core.handler.MyAuthenticationFailureHandler;
import com.yicj.core.handler.MyAuthenticationSuccessHandler;
import com.yicj.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

//WebSecurityConfigurerAdapter
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler ;
    @Autowired
    private MyAuthenticationFailureHandler failureHandler ;
    @Autowired
    private UserDetailsService userDetailsService ;

    @Autowired
    private SecurityProperties securityProperties ;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler ;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //1. 实例化并配置filter
        SmsCodeAuthenticationFilter smsAuthenticationFilter = new SmsCodeAuthenticationFilter() ;
        //   1.1 filter配置AuthenticationManager
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        //   1.2 成功失败处理器
        smsAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);

        //2. AuthenticationProvider实例化并配置
        SmsCodeAuthenticationProvider smsAuthenticationprovider = new SmsCodeAuthenticationProvider() ;
        //  2.1 配置userDetailsService
        smsAuthenticationprovider.setUserDetailsService(userDetailsService);

        //2. 将provider加入到AuthenticationManager管理的Provider集合中
        http.authenticationProvider(smsAuthenticationprovider) ;
        //3. 将smsFilter加入到UsernamePasswordAuthenticationFilter后面
        http.addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) ;
    }
}
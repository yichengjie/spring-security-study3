package com.yicj.browser.config;

import com.yicj.core.authentication.AbstractChannelSecurityConfig;
import com.yicj.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yicj.core.properties.SecurityProperties;
import com.yicj.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties ;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig ;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig ;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //密码登录的配置
        applyPasswordAuthenticationConfig(http);
        //其他配置
        http
            //短信验证相关配置
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            //图形验证相关配置
            .apply(validateCodeSecurityConfig)
                .and()
            .authorizeRequests()
                // 登录页面，和验证码页面不需要权限验证
                .antMatchers(securityProperties.getBrowser().getLoginPage(),
                        "/code/*","/hello")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf().disable() ;
    }


}

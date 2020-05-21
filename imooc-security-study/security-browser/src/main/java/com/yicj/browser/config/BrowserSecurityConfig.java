package com.yicj.browser.config;

import com.yicj.core.authentication.FormAuthenticationConfig;
import com.yicj.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yicj.core.authorize.AuthorizeConfigManager;
import com.yicj.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig ;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig ;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager ;
    @Autowired
    private SpringSocialConfigurer imoocSocialSecurityConfigurer ;
    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig ;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单登录的配置
        formAuthenticationConfig.configure(http);
        //其他配置
        http
            //图形验证相关配置
            .apply(validateCodeSecurityConfig)
                .and()
            //短信验证相关配置
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .apply(imoocSocialSecurityConfigurer)
                .and()
            .csrf().disable() ;
        authorizeConfigManager.config(http.authorizeRequests());
    }


}

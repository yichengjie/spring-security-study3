package com.yicj.app.config;

import com.yicj.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yicj.core.properties.SecurityConstants;
import com.yicj.core.properties.SecurityProperties;
import com.yicj.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
public class ImoocResourceServerConfig  extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties ;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig ;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig ;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler ;
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler ;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .permitAll()
                .and()
            //短信验证相关配置
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            //图形验证相关配置
            .apply(validateCodeSecurityConfig)
                .and()
            .authorizeRequests()
                // 登录页面，和验证码页面不需要权限验证
                .antMatchers(securityProperties.getBrowser().getLoginPage(),
                        "/code/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf().disable() ;
    }

}

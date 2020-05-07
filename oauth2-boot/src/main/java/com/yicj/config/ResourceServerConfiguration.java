package com.yicj.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(ResourceServerConfiguration.class);

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint ;

    @Bean
    public CustomLogoutSuccessHandler customLogoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    } ;

    @Override
    public void configure(HttpSecurity http) throws Exception {
       logger.info("=========================111111111=========");
       http.exceptionHandling()
               .authenticationEntryPoint(customAuthenticationEntryPoint)
               .and()
               .logout()
               .logoutUrl("/oauth/logout")
               .logoutSuccessHandler(customLogoutSuccessHandler())
               .and()
               .authorizeRequests()
               .antMatchers("/hello/").permitAll()
               .antMatchers("/secure/**").authenticated();
    }
}
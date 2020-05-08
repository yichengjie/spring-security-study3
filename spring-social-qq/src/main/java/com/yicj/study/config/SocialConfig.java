package com.yicj.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    // 申明自定义的SpringSocialConfigurer
    public SpringSocialConfigurer socialConfigurer(){

        return new CommonSpringSocialConfigurer() ;
    }




}

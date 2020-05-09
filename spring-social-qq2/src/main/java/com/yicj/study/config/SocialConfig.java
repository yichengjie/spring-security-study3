package com.yicj.study.config;

import com.yicj.study.component.CommonSpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    public static final String REGISTER_URI = "/register";
    public static final String FILTER_PROCESSES_URL = "/qqLogin";

    @Autowired
    private DataSource dataSource;

    //@Autowired(required = false)
    //private ConnectionSignUp connectionSignUp ;

    //申明自定义的SpringSocialConfigurer
    @Bean
    public SpringSocialConfigurer socialConfigurer(){
        return new CommonSpringSocialConfigurer(FILTER_PROCESSES_URL, REGISTER_URI) ;
    }


    // ProviderSignInUtils 是一个用于处理oauth登录逻辑的工具，主要通过doPostSignUp执行Connection持久化逻辑
    // 他并不会主动调用，而是由客户端自行决定调用的时机。例如，当spring social 跳转到配置好的signup页面时，即可调用
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator factoryLocator){
        return new ProviderSignInUtils(factoryLocator,getUsersConnectionRepository(factoryLocator)) ;
    }

    @Bean
    public UsersConnectionRepository usersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator){
        //申明一个基于jdbc的用户连接管理容器
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(
                dataSource, connectionFactoryLocator, Encryptors.noOpText());
        // connection 隐式注册实例
        //if(connectionSignUp != null){
        //   repository.setConnectionSignUp(connectionSignUp);
        //}
        return repository ;
    }


    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return usersConnectionRepository(connectionFactoryLocator) ;
    }
}

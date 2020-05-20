package com.yicj.core.social;

import com.yicj.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;
import javax.sql.DataSource;


@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private SecurityProperties securityProperties ;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator,Encryptors.noOpText()) ;
    }

    /**
     * 配置SocialAuthenticationFilter的配置
     * 社交登录配置类，供浏览器或app模块引入设计登录配置用。
     */
    @Bean
    public SpringSocialConfigurer imoocSocialSecurityConfigurer(){
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        return new ImoocSpringSocialConfigurer(filterProcessesUrl) ;
    }
}

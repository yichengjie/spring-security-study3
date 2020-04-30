package com.yicj.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

//启动基于Redis的HttpSession实现
@EnableRedisHttpSession
public class HttpSessionConfig {

    @Autowired
    private FindByIndexNameSessionRepository sessionRepository ;

    //提供Redis连接，默认localhost:6379
    @Bean
    public RedisConnectionFactory connectionFactory(){
        return new JedisConnectionFactory() ;
    }

    //SpringSessionBackedSessionRegistry是session为Spring Security提供的
    //用于在集群环境下控制会话并发的会话注册实现类
    @Bean
    public SpringSessionBackedSessionRegistry sessionRegistry(){

        return new SpringSessionBackedSessionRegistry(sessionRepository) ;
    }

    //HttpSession 的事件监听，改为session提供的会话注册表
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher() ;
    }

}

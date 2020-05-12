package com.yicj.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//启动基于Redis的HttpSession实现
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    @Bean
    public RedisConnectionFactory connectionFactory(){
        return new JedisConnectionFactory() ;
    }

    //HttpSession 的事件监听，改为session提供的会话注册表
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher() ;
    }

}

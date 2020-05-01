package com.yicj.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

//启动基于Redis的HttpSession实现
@Configuration
//@EnableRedisHttpSession
public class HttpSessionConfig {

    @Value("${jetcache.remote.default.host}")
    private String redisHost;

    @Value("${jetcache.remote.default.port}")
    private int redisPort;

    @Value("${jetcache.remote.default.password}")
    private String redisPwd;

    //@Autowired
    //private FindByIndexNameSessionRepository sessionRepository ;

    @Bean
    public JedisConnectionFactory connectionFactory() {
        System.out.println("======> redisHost : " + redisHost);
        System.out.println("======> redisPort : " + redisPort);
        System.out.println("======> redisPwd : " + redisPwd);

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPwd));
        redisStandaloneConfiguration.setPort(redisPort);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

//    //HttpSession 的事件监听，改为session提供的会话注册表
//    @Bean
//    public HttpSessionEventPublisher httpSessionEventPublisher(){
//        return new HttpSessionEventPublisher() ;
//    }
//
//    //SpringSessionBackedSessionRegistry是session为Spring Security提供的
//    //用于在集群环境下控制会话并发的会话注册实现类
//    @Bean
//    public SpringSessionBackedSessionRegistry sessionRegistry(){
//
//        return new SpringSessionBackedSessionRegistry(sessionRepository) ;
//    }

}

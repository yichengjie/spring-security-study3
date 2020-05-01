package com.yicj.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//启动基于Redis的HttpSession实现
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

//    @Value("${jetcache.remote.default.host}")
//    private String redisHost;
//
//    @Value("${jetcache.remote.default.port}")
//    private int redisPort;
//
//    @Value("${jetcache.remote.default.password}")
//    private String redisPwd;

    //@Autowired
    //private FindByIndexNameSessionRepository sessionRepository ;

//    @Bean
//    public JedisConnectionFactory connectionFactory() {
//        System.out.println("======> redisHost : " + redisHost);
//        System.out.println("======> redisPort : " + redisPort);
//        System.out.println("======> redisPwd : " + redisPwd);
//
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(redisHost);
//        redisStandaloneConfiguration.setDatabase(0);
//        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPwd));
//        redisStandaloneConfiguration.setPort(redisPort);
//        return new JedisConnectionFactory(redisStandaloneConfiguration);
//    }

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

    /**
     * RedisTemplate配置
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        // 设置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);// key序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);// value序列化
        redisTemplate.setHashKeySerializer(stringSerializer);// Hash key序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);// Hash value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}

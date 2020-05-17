package com.yicj.app.config;

import com.yicj.app.jwt.ImoocJwtTokenEnhancer;
import com.yicj.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class TokenStoreConfig {

    /**
     * 使用redis存储token的配置，只有在imooc.security.oauth2.tokenStore配置为redis时生效
     */
    @Configuration
    @ConditionalOnProperty(prefix = "imooc.security.oauth2", name = "tokenStore", havingValue = "redis")
    public static class RedisConfig {

        @Autowired
        private RedisConnectionFactory redisConnectionFactory;

        /**
         * @return
         */
        @Bean
        public TokenStore redisTokenStore() {
            return new RedisTokenStore(redisConnectionFactory);
        }
    }


    //matchIfMissing:表示在配置文件中如果没有配置这个属性的时候下面的配置都生效
    @Configuration
    @ConditionalOnProperty(prefix = "imooc.security.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
    public static class JwtConfig {

        @Autowired
        private SecurityProperties securityProperties;

        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
            return converter;
        }

        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer(){
            return new ImoocJwtTokenEnhancer() ;
        }
    }

}

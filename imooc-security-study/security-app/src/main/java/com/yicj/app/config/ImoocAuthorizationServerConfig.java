package com.yicj.app.config;

import com.yicj.core.properties.OAuth2ClientProperties;
import com.yicj.core.properties.SecurityProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class  ImoocAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private UserDetailsService userDetailsService ;

    @Autowired
    private TokenStore tokenStore ;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter ;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer ;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .tokenStore(tokenStore)
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
        ;
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer !=null){
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain() ;
            List<TokenEnhancer> enhancers = new ArrayList<>() ;
            enhancers.add(jwtTokenEnhancer) ;
            enhancers.add(jwtAccessTokenConverter) ;
            enhancerChain.setTokenEnhancers(enhancers);

            endpoints
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter) ;
        }
    }

    /**
     * 客户端配置
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
            for (OAuth2ClientProperties client : securityProperties.getOauth2().getClients()) {
                builder.withClient(client.getClientId())
                        .secret(client.getClientSecret())
                        .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                        .accessTokenValiditySeconds(client.getAccessTokenValidateSeconds())
                        .refreshTokenValiditySeconds(2592000)
                        .scopes("all");
            }
        }
    }
}

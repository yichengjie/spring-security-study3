package com.yicj.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

@Profile("simple")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private PasswordEncoder passwordEncoder ;


    /**
     * 配置授权服务器的安全，意味着/oauth/token端点和/oauth/authorize端点都应该是安全的。
     * 默认的配置覆盖了绝大多数的需求，所以一般情况下不需要做任何事情
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    /**
     * 该处通过配置ClientDetailsServiceConfigurer来配置注册到这个授权服务器的客户端clients信息，
     * 注意，除非在下面的configure(AuthorizationServerEndpointsConfigurer endpoints)中指定了
     * 一个AuthenticationManager，否则密码授权模式不可用
     *
     * 至少要配置一个client，否则无法启动服务器
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
            //client_id
            .withClient("client-for-server")
            //client_secret
            .secret(passwordEncoder.encode("client-for-server"))
            //该client支持的授权模式。OAuth的client在请求code时，
            //只有传递授权模式参数，该处包含的授权模式才可以访问
            .authorizedGrantTypes("authorization_code","implicit","password","client_credentials","refresh_token")
            // 该client分配的access_token的有效时间要少于刷新时间
            .accessTokenValiditySeconds(7200)
            //该client分配的access_token的可刷新时间要多于有效时间
            //超过有效时间，但在可刷新时间范围内的access_token也可以刷新
            .refreshTokenValiditySeconds(72000)
            //重定向url
            .redirectUris("http://localhost:8080/login/oauth2/code/authorizationserver")
            .additionalInformation()
            //该client可以访问的资源服务器id，每个资源服务器都有一个id
            .resourceIds(ResourceServerConfig.RESOURCE_ID)
            //该client拥有的权限，资源服务器可以依据该处定义的权限对client进行鉴权
            .authorities("ROLE_CLIENT")
            //该client可以访问的资源的范围,资源服务器可以依据该处定义的范围对client进行鉴权
            .scopes("profile","email","phone","aaa")
            //自动批准的范围(scope)，自动批准的scope在批准页不需要显示，
            // 即不需要用户确认批准。如果所有scope都自动批准，则不显示批准页
            .autoApprove("profile") ;
    }

    /**
     * 该方法是用来配置授权服务器特性的 (AuthorizationServer endpoints),
     * 主要是一些非安全的特性，比如token存储,token自定义，授权模式等。
     * 默认不需要任何配置，如果需要密码授权，则需要提供一个AuthenticationManager
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        // 启用password模式认证
        endpoints.authenticationManager(authenticationManager)  ;
        endpoints.tokenEnhancer(accessTokenEnhancer()) ;
        endpoints.tokenStore(tokenStore()) ;
    }

    private TokenStore tokenStore(){
        InMemoryTokenStore tokenStore = new InMemoryTokenStore();
        tokenStore.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator(){
            private static final String CLIENT_ID = "client_id";
            private static final String SCOPE = "scope";
            private static final String USERNAME = "username";

            @Override
            public String extractKey(OAuth2Authentication authentication) {
                Map<String, String> values = new LinkedHashMap<String, String>();
                OAuth2Request authorizationRequest = authentication.getOAuth2Request();
                if (!authentication.isClientOnly()) {
                    values.put(USERNAME, authentication.getName() + System.currentTimeMillis());
                }
                values.put(CLIENT_ID, authorizationRequest.getClientId());
                if (authorizationRequest.getScope() != null) {
                    values.put(SCOPE, OAuth2Utils.formatParameterList(new TreeSet<String>(authorizationRequest.getScope())));
                }
                return generateKey(values);
            }
        });
        return tokenStore ;
    }



    private TokenEnhancer accessTokenEnhancer(){
        //OAuth2AccessToken token, OAuth2Authentication authentication
        return (token, authentication) -> {
            //自定义一些token的内容
            Map<String, Object> map = new HashMap<>();
            map.put("author", "yicj");
            map.put("address", "BJS") ;
            map.put("organization", "developer");
            return token ;
        } ;
    }


}

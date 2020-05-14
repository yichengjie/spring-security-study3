package com.yicj.client.connect;

import org.springframework.security.oauth2.client.endpoint.NimbusAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;

import java.util.HashMap;
import java.util.Map;


/**
 * OAuth2AccessTokenResponseClient的组合类，使用了Composite Pattern（组合模式）。
 * 除了支持Google、Okta、Github和Facebook外，还支持QQ、微信等多种认证服务器,
 * 可根据registrationId选择响应的OAuthAccessTokenResponseClient
 */
public class CompositeOauth2AccessTokenResponseClient implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

    private static final String DefaultClientKey = "default_key";

    private Map<String,OAuth2AccessTokenResponseClient> clients ;

    public CompositeOauth2AccessTokenResponseClient(){
        this.clients = new HashMap<>() ;
        // spring-security-oauth2-client默认OAuth2AccessTokenResponseClient
        // 实现类是NimbusAuthorizationCodeTokenResponseClient
        // 将其预置到组合类CompositeOauth2AccessTokenResponseClient中，
        // 使其默认支持Google、Okta、GitHub和Facebook
        this.clients.put(DefaultClientKey, new NimbusAuthorizationCodeTokenResponseClient()) ;
    }

    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
        ClientRegistration clientRegistration = authorizationGrantRequest.getClientRegistration();
        OAuth2AccessTokenResponseClient client = clients.get(clientRegistration.getRedirectUriTemplate());
        if (client == null){
            client = clients.get(DefaultClientKey) ;
        }
        return client.getTokenResponse(authorizationGrantRequest);
    }

    public Map<String,OAuth2AccessTokenResponseClient>  getOAuth2AccessTokenResponseClients(){

        return clients ;
    }
}

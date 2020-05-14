package com.yicj.client.connect.qq;

import com.yicj.client.component.TextHtmlHttpMessageConverter;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义实现OAuth2AccessTokenResponseClient接口
 * OAuth2AccessTokenResponseClient实现了以code交换access_token的具体逻辑
 */
public class QQOauth2AccessTokenResponseClient implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

    private RestTemplate restTemplate ;

    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {

        ClientRegistration clientRegistration = authorizationGrantRequest.getClientRegistration() ;
        OAuth2AuthorizationExchange oAuth2AuthorizationExchange = authorizationGrantRequest.getAuthorizationExchange() ;
        // 根据api文档获取请求access_token参数
        MultiValueMap<String,String> params = new LinkedMultiValueMap<> () ;
        params.set("client_id", clientRegistration.getClientId());
        params.set("client_secret", clientRegistration.getClientSecret());
        params.set("code", oAuth2AuthorizationExchange.getAuthorizationResponse().getCode());
        params.set("redirect_uri",oAuth2AuthorizationExchange.getAuthorizationRequest().getRedirectUri());
        params.set("grant_type","authorization_code");
        String responseStr = this.getRestTemplate().postForObject(
                clientRegistration.getProviderDetails().getTokenUri(),params,String.class) ;
        // 从api文档中可以解析accessToken的方式
        String[] infos = responseStr.split("&");
        String accessToken = infos[0].substring(infos[0].lastIndexOf("=") +1) ;
        Long expireIn = new Long(infos[1].lastIndexOf("=") +1) ;
        Set<String> scopes = new LinkedHashSet<>(
                oAuth2AuthorizationExchange.getAuthorizationRequest().getScopes()
        ) ;
        Map<String,Object> additionalParamters = new LinkedHashMap<>() ;
        OAuth2AccessToken.TokenType tokenType = OAuth2AccessToken.TokenType.BEARER;

        return OAuth2AccessTokenResponse.withToken(accessToken)
                .tokenType(tokenType)
                .expiresIn(expireIn)
                .scopes(scopes)
                .additionalParameters(additionalParamters)
                .build();
    }


    private RestTemplate getRestTemplate(){
        if (restTemplate == null){
            restTemplate = new RestTemplate() ;
            restTemplate.getMessageConverters().add(new TextHtmlHttpMessageConverter()) ;
        }
        return restTemplate ;
    }

}

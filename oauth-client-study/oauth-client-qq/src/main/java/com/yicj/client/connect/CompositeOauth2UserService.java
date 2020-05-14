package com.yicj.client.connect;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashMap;
import java.util.Map;

public class CompositeOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private static final String DefaultUserServiceKey = "default_key" ;

    private Map<String,OAuth2UserService> userServices ;

    public CompositeOauth2UserService(){
        userServices = new HashMap<>() ;
        // DefaultOauth2UserService是默认处理OAuth或区域用户逻辑的Oauth2UserService实现类
        // 将其预置到组合类CompositeOauth2UserService中，从而默认支持Google、Okta、GitHub和Facebook
        this.userServices.put(DefaultUserServiceKey, new DefaultOAuth2UserService()) ;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService service = userServices.get(clientRegistration.getRegistrationId());
        if (service == null){
            service = userServices.get(DefaultUserServiceKey) ;
        }
        return service.loadUser(userRequest) ;
    }

    public Map<String,OAuth2UserService> getUserServices(){
        return userServices ;
    }
}

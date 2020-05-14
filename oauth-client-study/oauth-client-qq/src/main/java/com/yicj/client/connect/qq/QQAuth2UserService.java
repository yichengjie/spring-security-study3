package com.yicj.client.connect.qq;

import com.yicj.client.component.JacksonFromTextHttpMessageConverter;
import com.yicj.client.model.QQUserInfo;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.client.RestTemplate;

/**
 * 实现OAuth2UserService
 */
public class QQAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    //获取openId的api
    //private static final String QQ_URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token={accessToken}";
    //获取用户信息的api (appId也就是client_id)
    private static final String QQ_URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?access_token={accessToken}&oauth_consumer_key={appId}&openid={openId}";

    private RestTemplate restTemplate ;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 第一步：获取openId接口响应
        String accessToken = userRequest.getAccessToken().getTokenValue();
        String openIdUrl = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUri() + "access_token={accessToken}" ;
        String result = getRestTemplate().getForObject(openIdUrl, String.class);
        //提取openId
        String openId = result.substring(result.lastIndexOf(":\"") + 2, result.indexOf("\"}")) ;
        //第二步获取用户信息
        String appId = userRequest.getClientRegistration().getClientId();
        QQUserInfo qqUserInfo = this.getRestTemplate()
                .getForObject(QQ_URL_GET_USER_INFO, QQUserInfo.class, appId, openId, accessToken) ;
        //为用户信息类补充openId
        if (qqUserInfo != null){
            qqUserInfo.setOpenId(openId);
        }
        return qqUserInfo;
    }

    private RestTemplate getRestTemplate(){
        if (restTemplate == null){
            restTemplate = new RestTemplate() ;
            // 通过Jackson JSON processing library直接将返回值绑定到对象
            restTemplate.getMessageConverters().add(new JacksonFromTextHttpMessageConverter()) ;
        }
        return restTemplate ;
    }
}

package com.yicj.study.connect;

import com.yicj.study.component.TextHtmlHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class QQOAuth2Template extends OAuth2Template {

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        //指明获取accessToken时，需要携带client_id 和client_secret两个参数
        // （对应申请QQ互联的appId和appKey）
        setUseParametersForClientAuthentication(true);
    }


    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        //获取accessToken接口的响应
        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class) ;
        // 从api文档中可以获取解析accessToken的方式
        String[] infos = responseStr.split("&");
        String accessToken = infos[0].substring(infos[0].lastIndexOf("=") +1) ;
        Long expireIn = new Long(infos[1].lastIndexOf("=") +1) ;
        String refreshToken = infos[2].substring(infos[2].lastIndexOf("=") + 1) ;
        return new AccessGrant(accessToken,null, refreshToken, expireIn);
    }


    /**
     * 处理text/html类型的数据
     * @return
     */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate() ;
        restTemplate.getMessageConverters().add(new TextHtmlHttpMessageConverter()) ;
        return restTemplate;
    }
}

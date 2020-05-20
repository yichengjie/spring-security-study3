package com.yicj.core.social.qq.connect;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

public class QQOAuth2Template extends OAuth2Template {

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        //指明获取accessToken时，需要携带client_id 和client_secret两个参数
        // （对应申请QQ互联的appId和appKey）
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String result = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(result, "&");
        String accessToken = StringUtils.substringAfter(items[0],"=") ;
        String expireIn = StringUtils.substringAfter(items[1],"=") ;
        String refreshToken = StringUtils.substringAfter(items[2],"=") ;
        return new AccessGrant(accessToken,null, refreshToken, Long.parseLong(expireIn));
    }

    /**
     * 处理text/html类型的数据
     * @return
     */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate() ;
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8"))) ;
        return restTemplate;
    }
}
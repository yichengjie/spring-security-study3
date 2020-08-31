package com.yicj.study.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yicj.study.entity.QQUserInfo;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
public class HelloOauthController {

    private static final String CLIENT_ID = "100550231" ;
    private static final String CLIENT_SECRET = "69b6ab57b22f3c2fe6a6149274e3295e" ;
    private static final String REDIRECT_URI = "http://www.pinzhi365.com/qqLogin/callback.do" ;

    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private ObjectMapper objectMapper ;

    @PostConstruct
    public void init(){
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
    }


    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper() ;
        String str = "{\"client_id\":\"100550231\",\"openid\":\"A1DC7A0D50010C510BE686794EE766DA\"}" ;

        Map map = objectMapper.readValue(str, Map.class);
        System.out.println(map);

    }

    /**
     * 获取授权码跳转地址
     * @param code
     * @return
     */
    @GetMapping("/qqLogin/callback.do")
    public QQUserInfo callback(String code) throws IOException {
        //1. 通过授权码获取Access Token
        String accessToken = this.getAccessToken(code);
        //2. 使用Access Token来获取用户的OpenID
        //{"client_id":"100550231","openid":"A1DC7A0D50010C510BE686794EE766DA"}
        Map<String,String> openIdAndClientIdMap = this.getOpenIdAndClientId(accessToken);
        String clientId = openIdAndClientIdMap.get("client_id") ;
        String openId = openIdAndClientIdMap.get("openid") ;
        //3 .使用Access Token以及OpenID来访问和修改用户数据
        QQUserInfo userInfo = this.getUserInfo(openId, clientId, accessToken);
        return userInfo ;
    }


    /**
     * 使用Access Token以及OpenID来访问和修改用户数据
     * @return
     */
    /*private QQUserInfo getUserInfo2(String openId, String clientId, String accessToken){
        String url = "https://graph.qq.com/user/get_user_info?oauth_consumer_key={oauth_consumer_key}&openid={openid}" ;
        QQUserInfo userInfo = restTemplate.getForObject(url, QQUserInfo.class, clientId, openId);
        return userInfo ;
    }*/

    private QQUserInfo getUserInfo(String openId, String clientId, String accessToken){
        //String url = "https://graph.qq.com/user/get_user_info?access_token={access_token}&oauth_consumer_key={oauth_consumer_key}&openid={openid}" ;
        String url = "https://graph.qq.com/user/get_user_info?access_token={access_token}&oauth_consumer_key={oauth_consumer_key}&openid={openid}" ;
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken) ;
        params.put("oauth_consumer_key", clientId) ;
        params.put("openid", openId) ;
        //QQUserInfo userInfo = restTemplate.getForObject(url, QQUserInfo.class, params);
        QQUserInfo userInfo = restTemplate.getForObject(url, QQUserInfo.class, accessToken, clientId, openId);
        return userInfo ;
    }


    /**
     * 使用Access Token来获取用户的OpenID
     * @return
     */
    private Map<String,String> getOpenIdAndClientId(String accessToken) throws IOException {
        String url = "https://graph.qq.com/oauth2.0/me?access_token={access_token}" ;
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken) ;
        String ret = restTemplate.getForObject(url, String.class, params);
        //callback( {"client_id":"100550231","openid":"A1DC7A0D50010C510BE686794EE766DA"} );
        ret = StringUtils.substringBetween(ret,"(" ,")") ;
        ret = ret.trim() ;
        Map<String,String> map = objectMapper.readValue(ret, Map.class);
        log.info("ret msg : {}", map);
        return map ;
    }


    /**
     * 通过授权码获取Access Token
     * @param code
     * @return
     */
    private String getAccessToken(String code){
        String url = "https://graph.qq.com/oauth2.0/token" +
                "?grant_type=authorization_code" +
                "&client_id={client_id}" +
                "&client_secret={client_secret}" +
                "&code={code}" +
                "&state={state}" +
                "&redirect_uri={redirect_uri}";// tag7
        Map<String, String> params = new HashMap<>();
        params.put("client_id", CLIENT_ID);
        params.put("client_secret", CLIENT_SECRET);
        params.put("code", code);
        params.put("state", "test");
        params.put("redirect_uri", REDIRECT_URI);
        String ret = restTemplate.getForObject(url, String.class, params);
        log.info("org ret : {}" ,ret);
        ret = ret.substring(0, ret.indexOf('&'));
        ret = StringUtils.substringAfter(ret,"=") ;
        log.info("access token : {}", ret);
        return ret;
    }





    public class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public WxMappingJackson2HttpMessageConverter(){
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_HTML);
            setSupportedMediaTypes(mediaTypes);// tag6
        }
    }


}

package com.yicj.study.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
@Slf4j
public class HelloOauthController {

    /**
     * imooc.security.social.qq.app-id = 100550231
     * imooc.security.social.qq.app-secret = 69b6ab57b22f3c2fe6a6149274e3295e
     * imooc.security.social.qq.providerId = callback.do
     */
    private static final String CLIENT_ID = "100550231" ;
    private static final String CLIENT_SECRET = "69b6ab57b22f3c2fe6a6149274e3295e" ;

    @Autowired
    private RestTemplate restTemplate ;

    @GetMapping("/redirect")
    public Object redirect(String code)  {
        log.info("=====> 授权服务器返回授权码: code : " + code);
        Map<String, String> tokenMap = getAccessToken(code);
        String access_token = tokenMap.get("access_token") ;
        log.info("======>  access_token ： " + access_token);
        Object userInfo = this.getUserInfo(access_token);
        return userInfo ;
    }

    /**
     * 获取用户信息
     * @return
     */
    private Object getUserInfo(String accessToken){
        String url = "https://api.github.com/user" ;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("Authorization","token " + accessToken);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class);
        return exchange.getBody() ;
    }

    /**
     * 获取access token
     * @param code
     * @return
     */
    private Map<String,String> getAccessToken(String code){
        //1. 组装http header信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        //2. 准备http url
        String urlFormat = "https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s" ;
        String url = String.format(urlFormat, CLIENT_ID, CLIENT_SECRET, code);

        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
        //3. 发送请求
        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class);
        return exchange.getBody()  ;
    }



}

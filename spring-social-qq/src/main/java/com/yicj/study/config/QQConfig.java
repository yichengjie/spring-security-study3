package com.yicj.study.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties("spring.social.qq")
public class QQConfig {

//    @Bean //必须new 一个RestTemplate并放入spring容器当中,否则启动时报错
//    public RestTemplate restTemplate() {
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
//        httpRequestFactory.setConnectTimeout(30 * 3000);
//        httpRequestFactory.setReadTimeout(30 * 3000);
//        return new RestTemplate(httpRequestFactory);
//    }

    @Setter
    @Getter
    private String providerId = "qq";
    @Setter
    @Getter
    private String appId ;
    @Setter
    @Getter
    private String appSecret ;




}

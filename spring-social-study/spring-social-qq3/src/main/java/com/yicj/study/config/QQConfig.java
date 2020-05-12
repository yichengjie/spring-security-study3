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
    @Setter
    @Getter
    private String providerId = "qq";
    @Setter
    @Getter
    private String appId ;
    @Setter
    @Getter
    private String appSecret ;
    @Setter
    @Getter
    private String redirectUrl ;
}

package com.yicj.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

/**
 * <p>
 * AppConfig
 * </p>
 *
 * @author yicj
 * @since 2024/8/27 21:59
 */
@Configuration
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login() ;
        //
        http.authorizeRequests()
                .anyRequest()
                .authenticated() ;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        ClientRegistration clientRegistration = clientRegistration();
        return new InMemoryClientRegistrationRepository(clientRegistration) ;
    }

    private ClientRegistration clientRegistration(){
        return ClientRegistration.withRegistrationId("github")
                .clientId("client-id")
                .clientSecret("client-secret")
                .scope("read:user")
                .authorizationUri("https://github.com/login/oauth/authorize")
                .tokenUri("https://github.com/login/oauth/access_token")
                .userInfoUri("https://api.github.com/user")
                .userNameAttributeName("id")
                .clientName("Github")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                .build() ;
    }
}

package com.yicj.study.config;


import com.yicj.study.model.CustomOAuth2AuthenticationDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetailsSource;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.servlet.http.HttpServletRequest;

/**
 * 资源服务器的职责是对来自OAuth客户端的access_token进行鉴权。一个资源服务器包含多个端点(接口),
 * 一部分端点作为资源服器的资源提供给OAuth的client访问，另一部分端点不由资源服务器管理。
 * 由资源服务器管理的端点安全性配置在此类中，其余端点的安全配置在SecurityConfiguration类中。
 * 当请求中包含OAuth2 access_token时，Spring Security会根据WebSecurityConfigurationAdapter，
 * 执行顺序(Order)是3.在SecurityConfiguration类之前执行，优先级更高
 */
@Slf4j
@Profile("simple")
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "authorizationserver" ;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.resourceId(RESOURCE_ID);
        resources.authenticationDetailsSource(authenticationDetailsSource()) ;
        resources.tokenStore(new InMemoryTokenStore(){

        }) ;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        log.info("ResourceServerConfig中配置HttpSecurity对象执行");
        // 只有/me端点作为资源服务器的资源
        http.requestMatchers().antMatchers("/me")
            .and()
            .authorizeRequests()
            .anyRequest().authenticated() ;
    }

    private AuthenticationDetailsSource<HttpServletRequest, OAuth2AuthenticationDetails> authenticationDetailsSource(){
        return new OAuth2AuthenticationDetailsSource(){
            @Override
            public OAuth2AuthenticationDetails buildDetails(HttpServletRequest context) {
                CustomOAuth2AuthenticationDetails details = new CustomOAuth2AuthenticationDetails(context);
                String tokenValue = details.getTokenValue();
                // 根据token value 从redis中获取用户信息
                details.setAuthor("yicj");
                details.setAddress("BJS");
                details.setOrganization("developer");
                return details ;
            }
        } ;
    }
}

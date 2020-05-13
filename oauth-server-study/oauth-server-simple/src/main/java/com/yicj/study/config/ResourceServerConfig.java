package com.yicj.study.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器的职责是对来自OAuth客户端的access_token进行鉴权。一个资源服务器包含多个端点(接口),
 * 一部分端点作为资源服器的资源提供给OAuth的client访问，另一部分端点不由资源服务器管理。
 * 由资源服务器管理的端点安全性配置在此类中，其余端点的安全配置在SecurityConfiguration类中。
 * 当请求中包含OAuth2 access_token时，Spring Security会根据WebSecurityConfigurationAdapter，
 * 执行顺序(Order)是3.在SecurityConfiguration类之前执行，优先级更高
 */
@Profile("simple")
@Configuration
@EnableResourceServer
@Slf4j
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "authorizationserver" ;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.resourceId(RESOURCE_ID) ;
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
}

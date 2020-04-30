package com.yicj.redis.config;

import com.yicj.redis.component.RedisSessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1900)
public class MvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private RedisSessionInterceptor redisSessionInterceptor ;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //所有已api开头的访问都要进入RedisSessionInterceptor拦截器进行登录验证，
        // 并排除login接口(全路径)。必须写成链式，分别设置的话会创建多个拦截器。
        //必须写成getSessionInterceptor()，否则SessionInterceptor中的@Autowired会无效
        registry.addInterceptor(redisSessionInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login");
        super.addInterceptors(registry);
    }

}

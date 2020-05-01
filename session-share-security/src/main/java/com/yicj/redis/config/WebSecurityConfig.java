package com.yicj.redis.config;

import com.yicj.redis.vercode.MyAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //注意这个不能放在HttpSessionConfig配置类中，否则会报错提示循环依赖
    @Autowired
    private FindByIndexNameSessionRepository sessionRepository ;

    //注意这个不能放在HttpSessionConfig配置类中，否则会报错提示循环依赖
    //SpringSessionBackedSessionRegistry是session为Spring Security提供的
    //用于在集群环境下控制会话并发的会话注册实现类
    @Bean
    public SpringSessionBackedSessionRegistry sessionRegistry(){
        return new SpringSessionBackedSessionRegistry(sessionRepository) ;
    }

    //注意这里一定要放在sessionRegistry()方法后面，否则会报错
    // 将新的会话注册表提供给Spring Security
    @Autowired
    private SpringSessionBackedSessionRegistry redisSessionRegistry;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
            .antMatchers("/admin/api/**").hasRole("ADMIN")
            .antMatchers("/user/api/**").hasRole("USER")
            .antMatchers("/app/api/**","/favicon.ico").permitAll()
            .anyRequest().authenticated()
            .and()
        .csrf().disable()
        .formLogin()
            .permitAll()
            .failureHandler(new MyAuthenticationFailureHandler())
            .and()
        .sessionManagement()
            .maximumSessions(1)
            //使用session提供的会话注册表
            //.sessionRegistry(sessionRegistry())
            .sessionRegistry(redisSessionRegistry)
        ;
    }

}

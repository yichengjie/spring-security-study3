package com.yicj.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
            .antMatchers("/","/home").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            //指定登录成功时的处理逻辑
            .successHandler((request, response, authentication)->{
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = response.getWriter();
                //{"error_code":"0","message":"欢迎登录系统"}
                out.write("{\"error_code\":\"0\",\"message\":\"欢迎登录系统\"}");
            })
            //指定登录失败时的处理逻辑
            .failureHandler((request, response, ex) ->{
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(401);
                PrintWriter out = response.getWriter();
                //输出失败原因
                //{"error_code":"401","name":"","message":""}
                out.write("{\"error_code\":\"401\",\"name\":\""+ ex.getClass()+"\",\"message\":\""+ ex.getMessage()+"\"}");
            })
            .permitAll()
            .and()
        .httpBasic()
            .and()
        .logout()
            .permitAll()
            .and()
        .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.inMemoryAuthentication()
            .withUser("user").password("123").roles("USER") ;
    }
}

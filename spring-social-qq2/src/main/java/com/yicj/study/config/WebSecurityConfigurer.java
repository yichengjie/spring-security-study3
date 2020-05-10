package com.yicj.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;
import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource ;

    @Autowired
    private SpringSocialConfigurer customSpringSocialConfigurer ;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                // 放开spring social过滤器相关的url访问权限
                .antMatchers("/sigup.html", "/register", SocialConfig.FILTER_PROCESSES_URL+"/*").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/sigin.html")
                .loginProcessingUrl("/login/form")
                .permitAll()
                .and()
            .csrf().disable()
            //应用spring Social配置
            .apply(customSpringSocialConfigurer)
        ;
    }


}

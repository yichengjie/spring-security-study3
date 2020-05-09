package com.yicj.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
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
                .antMatchers("/index.html", SocialConfig.REGISTER_URI, SocialConfig.FILTER_PROCESSES_URL+"/*").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/index.html")
                .and()
            .csrf().disable()
            //应用spring Social配置
            .apply(customSpringSocialConfigurer)
        ;
    }



    @Bean
    public UserDetailsService userDetailsService(){
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager() ;
        manager.setDataSource(dataSource);
        if (!manager.userExists("user")){
            manager.createUser(User.withUsername("user").password("123").roles("USER").build());
        }
        if (!manager.userExists("admin")){
            manager.createUser(User.withUsername("admin").password("123").roles("USER","ADMIN").build());
        }
        return manager ;
    }
}

package com.yicj.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private DataSource dataSource ;

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
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

    @Bean
    public Producer captcha(){
        //配置图形验证码的基本参数
        Properties properties = new Properties() ;
        //图片宽度
        properties.setProperty("kaptcha.image.width","150") ;
        //图片长度
        properties.setProperty("kaptcha.image.height","50") ;
        //字符集
        properties.setProperty("kaptcha.textproducer.char.string","0123456789") ;
        //字符长度
        properties.setProperty("kaptcha.textproducer.char.length","4") ;
        Config config = new Config(properties);
        //使用默认的图片验证码实现，当然也可以自定义实现
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha() ;
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance() ;
    }
}

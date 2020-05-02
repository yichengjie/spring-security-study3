package com.yicj.core.validate.code;

import com.yicj.core.properties.SecurityProperties;
import com.yicj.core.validate.code.image.ImageCodeGenerator;
import com.yicj.core.validate.code.sms.DefaultSmsCodeSender;
import com.yicj.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeConfigBean {

    @Autowired
    private SecurityProperties securityProperties ;


    @Bean(name = "imageCodeGenerator")
    @ConditionalOnMissingBean(name = "imageCodeGenerator" )
    public ValidateCodeGenerator validateCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator() ;
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator ;
    }


    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        DefaultSmsCodeSender sender = new DefaultSmsCodeSender() ;
        return sender ;
    }
}

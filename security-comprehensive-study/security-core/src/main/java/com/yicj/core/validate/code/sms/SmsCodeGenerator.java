package com.yicj.core.validate.code.sms;

import com.yicj.core.properties.SecurityProperties;
import com.yicj.core.validate.code.ValidateCode;
import com.yicj.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties ;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        //验证码长度
        int length = securityProperties.getCode().getSms().getLength();
        //验证码
        String code = RandomStringUtils.randomNumeric(length);
        //过期时间
        int expireIn = securityProperties.getCode().getSms().getExpireIn();
        return new ValidateCode(code,expireIn);
    }
}

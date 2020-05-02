package com.yicj.core.vercode;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MyAuthenticationProvider extends DaoAuthenticationProvider {

    //构造方法注入UserDetailService和PasswordEncoder
    public MyAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
        UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // 实现图形验证码的校验逻辑
        MyWebAuthenticationDetails details = (MyWebAuthenticationDetails)authentication.getDetails();
        String imageCode = details.getImageCode() ;
        String saveImageCode = details.getSaveImageCode() ;
        //检验图片验证码
        if (StringUtils.isEmpty(imageCode) || StringUtils.isEmpty(saveImageCode) || !imageCode.equals(saveImageCode)){
            throw new VerificationCodeException() ;
        }
        // 调用父类方法完成密码验证
        super.additionalAuthenticationChecks(userDetails,authentication);
    }
}

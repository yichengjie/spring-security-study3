package com.yicj.vercode;


import org.springframework.security.core.AuthenticationException;

//自定义一个验证码校验失败的异常
public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException(){
        super("图形验证码校验失败");
    }
}

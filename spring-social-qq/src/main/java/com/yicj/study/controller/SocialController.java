package com.yicj.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.ServletWebRequest;

@Controller
public class SocialController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils ;

    @GetMapping("/register")
    public String socialRegister(ServletWebRequest  request){
        // 通过request对象获取Connect
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        // 执行Connection持久化
        providerSignInUtils.doPostSignUp(connection.getKey().getProviderUserId(), request);
        //执行绑定账号，信息完善等其他逻辑
        //....
        // 跳转页面
        return "redirect:/" ;
    }

}

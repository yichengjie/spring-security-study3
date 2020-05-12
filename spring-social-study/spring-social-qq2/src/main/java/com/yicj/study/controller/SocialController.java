package com.yicj.study.controller;

import com.yicj.study.config.SocialConfig;
import com.yicj.study.entity.UserEntity;
import com.yicj.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.ServletWebRequest;


@Controller
@Slf4j
public class SocialController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils ;

    @Autowired
    private UserService userService ;


    private RequestCache requestCache = new HttpSessionRequestCache() ;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy() ;

    /**
     * 注册页面
     * @return
     */
    @GetMapping(SocialConfig.REGISTER_URI)
    public String socialRegister(){
        System.out.println("hello world");
        return "sigup" ;
    }

    /**
     * 首次使用qq登录的时候会跳到注册页面
     * @param request
     * @return
     */
    @PostMapping(SocialConfig.REGISTER_URI)
    public String socialRegister(ServletWebRequest  request,String username, String password){
        //已经使用静默登录后这里不需要再次保存
        // 1. 通过request对象获取Connect
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        // 2. 执行Connection持久化，并绑定username
        providerSignInUtils.doPostSignUp(username, request);
        // 3. 用户信息持久化
        UserEntity userEntity = new UserEntity(username,password) ;
        userService.register(userEntity);
        // 跳转页面
        return "redirect:/sigin.html" ;
    }
}

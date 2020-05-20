package com.yicj.controller;

import com.yicj.model.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils ;

    @GetMapping("/api/me")
    public Object getCurrentUser(){

        return SecurityContextHolder.getContext().getAuthentication() ;
    }

    @GetMapping("/api/me2")
    public Object getCurrentUser2(Authentication authentication){

        return authentication ;
    }

    @GetMapping("/api/me3")
    public Object getCurrentUser3(@AuthenticationPrincipal UserDetails details){

        return details ;
    }

    @GetMapping("/api/hello")
    public String hello(){
        return "hello, user" ;
    }

    /**
     * 用户注册
     * @param form
     */
    @PostMapping("/regist")
    public void regist(UserForm form, HttpServletRequest request){
        //不管是注册还是绑定，都会拿到一个用户唯一标识
        String userId = form.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }
}

package com.yicj.controller;

import com.yicj.model.UserForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

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

    @PostMapping("/regist")
    public void regist(UserForm form){

    }
}

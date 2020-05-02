package com.yicj.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api")
public class UserController {

    @GetMapping("/me")
    public Object getCurrentUser(){

        return SecurityContextHolder.getContext().getAuthentication() ;
    }

    @GetMapping("/me2")
    public Object getCurrentUser2(Authentication authentication){

        return authentication ;
    }

    @GetMapping("/me3")
    public Object getCurrentUser3(@AuthenticationPrincipal UserDetails details){

        return details ;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello, user" ;
    }
}

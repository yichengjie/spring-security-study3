package com.yicj.controller;

import com.yicj.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private SecurityProperties securityProperties ;

    //private RequestCache requestCache = new HttpSessionRequestCache() ;


    @GetMapping("/hello")
    public String hello(){
        System.out.println(" securityProperties ： " + securityProperties);
        return  "hello world" ;
    }

    //这里加上@ResponseStatus(HttpStatus.UNAUTHORIZED)后前台将无法获取到输出
    @GetMapping("/hello2")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String hello2(){
        System.out.println(" securityProperties ： " + securityProperties);
        return  "hello world 2" ;
    }

}

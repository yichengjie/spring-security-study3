package com.yicj.controller;

import com.yicj.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}

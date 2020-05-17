package com.yicj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello/api/")
public class HelloController {

    @GetMapping("/hello")
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    //注意:这里加上HttpStatus.UNAUTHORIZED后前台无法输出内容
    public String hello(){
        return "hello world" ;
    }
}

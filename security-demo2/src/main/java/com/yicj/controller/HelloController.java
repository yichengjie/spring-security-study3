package com.yicj.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello/api/")
public class HelloController {

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String hello(){

        return "hello" ;
    }
}

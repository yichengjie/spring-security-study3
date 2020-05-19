package com.yicj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
    
    @RequestMapping("/helloUI")
    public String hello() {
        return "hello.html";
    }


    @RequestMapping("/homeUI")
    public String home() {
        return "home.html";
    }

}
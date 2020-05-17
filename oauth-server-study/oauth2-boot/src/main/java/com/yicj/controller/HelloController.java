package com.yicj.controller;

import com.yicj.client.model.Permission;
import com.yicj.security.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    PermissionService permissionService ;

    @RequestMapping("/")
    public String index(){

        return "index" ;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello" ;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/abc")
    public List<Permission> getroles(){
        return permissionService.findByAdminUserId(1) ;
    }
}
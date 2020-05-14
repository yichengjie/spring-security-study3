package com.yicj.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 获取认证信息，当认证通过后，第三方应用可以请求的资源
 */
@Slf4j
@Profile("simple")
@RestController
public class ResourceController {


    @RequestMapping("/me")
    public Principal me(Principal principal){
        log.info("principal : {}" , principal.toString());
        return principal ;
    }

}

package com.yicj.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: AuthController
 * Description: TODO(描述)
 * Date: 2020/5/21 14:18
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/hello")
    public String hello(){
        return "hello, auth";
    }

}

package com.yicj.study.component;

import com.yicj.study.model.User;
import com.yicj.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 当用户通过未在系统中使用过的第三方账号登录时，则可以“静默注册”，实现ConnectionSignUp接口
 * 当用户再次登录时便不再跳转到signup页面，而是将Connection信息自动持久化到数据库中，
 * 并跳转回原来访问的页面
 *
 * 在静默注册的同时生成用户记录，并赋予用户一个合适的角色
 */
//@Component
public class ImplicitConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private UserService userService ;

    @Override
    public String execute(Connection<?> connection) {
        //隐式创建用户
        // 用户名为openId
        User user = new User(connection.getKey().getProviderUserId(), "ROLE_USER") ;
        userService.createUser(user) ;
        return connection.getKey().getProviderUserId() ;
    }
}

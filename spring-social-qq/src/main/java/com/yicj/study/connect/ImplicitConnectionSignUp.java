package com.yicj.study.connect;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 当用户通过未在系统中使用过的第三方账号登录时，则可以“静默注册”，实现ConnectionSignUp接口
 * 当用户再次登录时便不再跳转到signup页面，而是将Connection信息自动持久化到数据库中，
 * 并跳转回原来访问的页面
 */
@Component
public class ImplicitConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        // 用户名为openId
        return connection.getKey().getProviderUserId() ;
    }
}

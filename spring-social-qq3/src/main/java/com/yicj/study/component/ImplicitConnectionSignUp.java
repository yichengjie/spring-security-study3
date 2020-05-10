package com.yicj.study.component;

import com.yicj.study.entity.UserEntity;
import com.yicj.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

@Component
public class ImplicitConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private UserService userService ;

    @Override
    public String execute(Connection<?> connection) {
        //1. 根据社交用户信息默认创建用户并返回用户唯一标识
        UserEntity userEntity = new UserEntity(connection.getKey().getProviderUserId(),"123") ;
        userService.register(userEntity);
        //1. 返回创建新用户的id
        return connection.getKey().getProviderUserId();
    }
}

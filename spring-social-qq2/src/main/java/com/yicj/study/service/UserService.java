package com.yicj.study.service;

import com.yicj.study.entity.UserEntity;

public interface UserService {

    void addUser(UserEntity user) ;

    UserEntity findUserByName(String  username) ;
    /**
     * 用户注册
     * @param user
     */
    void register(UserEntity user) ;
}

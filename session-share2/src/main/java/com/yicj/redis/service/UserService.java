package com.yicj.redis.service;

import com.yicj.redis.model.User;

public interface UserService {

    User findUserByAccountAndPassword(String username, String password) ;

    User findUserByUserId(long userId) ;
}

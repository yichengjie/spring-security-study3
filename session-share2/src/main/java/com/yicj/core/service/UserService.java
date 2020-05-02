package com.yicj.core.service;

import com.yicj.core.model.User;

public interface UserService {

    User findUserByAccountAndPassword(String username, String password) ;

    User findUserByUserId(long userId) ;
}

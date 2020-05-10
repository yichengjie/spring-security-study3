package com.yicj.study.service;

import com.yicj.study.entity.User;

public interface UserService {

    void addUser(User user) ;

    User findByUsername(String  username) ;
}

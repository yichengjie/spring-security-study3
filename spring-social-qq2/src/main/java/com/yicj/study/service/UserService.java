package com.yicj.study.service;

import com.yicj.study.model.User;

public interface UserService {

    User findByUserName(String username) ;

    void createUser(User user);
}

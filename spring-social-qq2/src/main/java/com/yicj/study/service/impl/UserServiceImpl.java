package com.yicj.study.service.impl;

import com.yicj.study.model.User;
import com.yicj.study.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findByUserName(String username) {
        return null;
    }

    @Override
    public void createUser(User user) {

    }
}

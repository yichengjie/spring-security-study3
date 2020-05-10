package com.yicj.study.service.impl;

import com.yicj.study.dao.UserRepository;
import com.yicj.study.entity.UserEntity;
import com.yicj.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository ;

    @Override
    public void addUser(UserEntity user) {
        repository.save(user) ;
    }

    @Override
    public UserEntity findUserByName(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public UserEntity findUserById(Long id) {
        return repository.findOne(id);
    }

}

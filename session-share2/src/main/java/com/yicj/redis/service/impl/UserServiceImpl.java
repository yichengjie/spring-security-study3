package com.yicj.redis.service.impl;

import com.yicj.redis.model.User;
import com.yicj.redis.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static List<User> users = new ArrayList<>() ;
    static {
        users.add(new User(1001,"user1","123")) ;
        users.add(new User(1002,"user2","123")) ;
        users.add(new User(1003,"user3","123")) ;
    }

    @Override
    public User findUserByAccountAndPassword(String username, String password) {
        List<User> lst = users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .collect(Collectors.toList());
        if (lst.isEmpty()){
            return null ;
        }
        return lst.get(0);
    }

    @Override
    public User findUserByUserId(long userId) {
        List<User> lst = users.stream().filter(user -> user.getUserId() == userId)
                .collect(Collectors.toList());
        if (lst.isEmpty()){
            return null ;
        }
        return lst.get(0) ;
    }
}

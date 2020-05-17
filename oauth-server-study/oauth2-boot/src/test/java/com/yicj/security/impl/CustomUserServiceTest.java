package com.yicj.security.impl;

import com.yicj.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserServiceTest extends BaseTest {

    @Autowired
    private CustomUserService customUserService ;

    @Test
    public void loadUserByUsername() {
        UserDetails user = customUserService.loadUserByUsername("yicj");
        System.out.println(user);
    }
}
package com.yicj.service.impl;

import com.yicj.BaseTest;
import com.yicj.SpringbootOauth2Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


public class CustomUserServiceTest extends BaseTest {

    @Autowired
    private CustomUserService customUserService ;

    @Test
    public void loadUserByUsername() {
        UserDetails user = customUserService.loadUserByUsername("yicj");
        System.out.println(user);
    }
}
package com.yicj.study.service;

import com.yicj.study.SpringSocialQQApplication;
import com.yicj.study.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringSocialQQApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService ;

    @Test
    public void testFindByUsername() {
        String username = "yicj";
        UserEntity user = userService.findUserByName(username);
        log.info("user: {}", user);
    }



}

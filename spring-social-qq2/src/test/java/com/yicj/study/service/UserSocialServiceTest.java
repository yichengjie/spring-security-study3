package com.yicj.study.service;

import com.yicj.study.SpringSocialQQApplication;
import com.yicj.study.entity.User;
import com.yicj.study.entity.UserSocial;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringSocialQQApplication.class)
@Slf4j
public class UserSocialServiceTest {

    @Autowired
    private UserSocialService userSocialService ;

    @Autowired
    private UserService userService ;

    @Test
    public void testFindUserSocial(){
        String providerId = "QQ";
        String socialId = "123";
        UserSocial userSocial = userSocialService.findUserSocial(providerId, socialId);
        log.info("===> userSocial : {}", userSocial);
        Long userId = userSocial.getUserId();

        User user = userService.findUserById(userId);
        log.info("===> user : {} " , user);
    }

    @Test
    public void  testAddUserSocial(){
        UserSocial userSocial = new UserSocial() ;
        userSocial.setUserId(1L);
        userSocial.setProviderId("QQ");
        userSocial.setSocialId("123");
        userSocialService.addUserSocial(userSocial);
    }
}

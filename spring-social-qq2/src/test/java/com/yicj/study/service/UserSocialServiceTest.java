package com.yicj.study.service;

import com.yicj.study.SpringSocialQQApplication;
import com.yicj.study.entity.UserEntity;
import com.yicj.study.entity.UserSocialEntity;
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
        String socialId = "123";
        UserSocialEntity userSocial = userSocialService.findUserSocial(socialId);
        log.info("===> userSocial : {}", userSocial);
        String username = userSocial.getUsername() ;

        UserEntity user = userService.findUserByName(username);
        log.info("===> user : {} " , user);
    }

    @Test
    public void  testAddUserSocial(){
        UserSocialEntity userSocial = new UserSocialEntity() ;
        userSocial.setUsername("yicj");
        userSocial.setProviderId("QQ");
        userSocial.setSocialId("123");
        userSocialService.addUserSocial(userSocial);
    }
}

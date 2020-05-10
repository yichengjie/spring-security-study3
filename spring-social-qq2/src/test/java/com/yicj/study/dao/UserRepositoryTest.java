package com.yicj.study.dao;

import com.yicj.study.SpringSocialQQApplication;
import com.yicj.study.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringSocialQQApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository ;

    @Test
    public void testQueryAll(){
        List<User> list = userRepository.findAll();
        System.out.println(list);
    }

    @Test
    public void testSave(){
        User user = new User() ;
        user.setUsername("yicj");
        user.setPassword("123");
        user.setRoles("ROLE_USER");
        userRepository.save(user) ;
    }

}

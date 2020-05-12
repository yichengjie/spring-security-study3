package com.yicj.core;


import com.yicj.core.component.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisUtilTest {


    @Autowired
    private RedisUtil redisUtil ;


    @Test
    public void testSetName(){
        redisUtil.set("name","yicjxxx") ;
    }

    @Test
    public void tesGetName(){
        Object name = redisUtil.get("name");
        System.out.println("name : " + name);
    }

}

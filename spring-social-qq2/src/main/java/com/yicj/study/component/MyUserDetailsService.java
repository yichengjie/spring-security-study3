package com.yicj.study.component;

import com.yicj.study.entity.UserEntity;
import com.yicj.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户名{}", username);
        UserEntity userEntity = userService.findUserByName(username);
        if (userEntity ==null) {
            throw new UsernameNotFoundException("用户名不存在") ;
        }
        //根据用户名查找用户信息
        return new User(username,userEntity.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(userEntity.getRoles()));
    }
}

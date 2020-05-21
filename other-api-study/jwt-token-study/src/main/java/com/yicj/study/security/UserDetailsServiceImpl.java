package com.yicj.study.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * 获取 userDetail
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据查找到的用户信息判断用户是否被冻结
        String roles = "ROLE_USER" ;
        if ("admin".equals(username)){
            roles = "ROLE_USER,ROLE_ADMIN" ;
        }else if ("auth".equals(username)){
            roles = "";
        }
        return new User(username, "123",
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(roles));

    }
}
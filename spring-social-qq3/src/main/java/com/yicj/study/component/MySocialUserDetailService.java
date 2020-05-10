package com.yicj.study.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import java.util.Collection;


@Component
public class MySocialUserDetailService implements SocialUserDetailsService {

    @Autowired
    private UserDetailsService userDetailsService ;

    /**
     * connection表中的用户id，与user表中的username对应
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException {
        // 1. 通过社交账号绑定的用户id获取用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null){
            throw new UsernameNotFoundException("用户不存在") ;
        }
        String password = userDetails.getPassword() ;
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities() ;
        return new SocialUser(username, password, authorities) ;
    }
}

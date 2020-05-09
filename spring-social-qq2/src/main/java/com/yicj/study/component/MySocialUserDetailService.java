package com.yicj.study.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class MySocialUserDetailService implements SocialUserDetailsService {

    @Autowired
    private MyUserDetailsService userDetailsService ;

    @Override
    public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null){
            throw new UsernameNotFoundException("用户不存在") ;
        }
        String password = userDetails.getPassword() ;
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities() ;
        return new SocialUser(username, password, authorities) ;
    }
}

package com.yicj.study.component;

import com.yicj.study.entity.UserSocialEntity;
import com.yicj.study.service.UserSocialService;
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
    private UserSocialService userSocialService ;

    @Autowired
    private UserDetailsService userDetailsService ;

    @Override
    public SocialUserDetails loadUserByUserId(String socialId) throws UsernameNotFoundException {
        // 1 通过社交账号的openid获取用户id
        UserSocialEntity userSocialEntity = userSocialService.findUserSocial(socialId) ;
        String username = userSocialEntity.getUsername();
        // 2. 通过社交账号绑定的用户id获取用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null){
            throw new UsernameNotFoundException("用户不存在") ;
        }
        String password = userDetails.getPassword() ;
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities() ;
        return new SocialUser(username, password, authorities) ;
    }
}

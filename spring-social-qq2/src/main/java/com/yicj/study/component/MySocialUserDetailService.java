package com.yicj.study.component;

import com.yicj.study.model.User;
import com.yicj.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MySocialUserDetailService implements SocialUserDetailsService {

    @Autowired
    private UserService userService ;

    @Override
    public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在") ;
        }
        //设置权限
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles());
        user.setAuthorities(authorities);
        return user;
    }
}

package com.yicj.study.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class User implements SocialUserDetails , UserDetails {

    @Setter
    private String userId ;
    @Setter
    private String password ;
    @Setter
    private String username ;
    @Setter
    private boolean accountNonExpired ;
    @Setter
    private boolean accountNonLocked ;
    @Setter
    private boolean credentialsNonExpired ;
    @Setter
    private boolean enabled ;
    @Setter
    @Getter
    private String roles ;
    @Getter
    @Setter
    private List<GrantedAuthority> authorities = Collections.EMPTY_LIST;


    public User(String userId, String roles){
        this.userId = userId ;
        this.roles = roles ;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

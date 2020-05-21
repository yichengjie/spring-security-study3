package com.yicj.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService ;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String mobile = (String)authentication.getPrincipal();
        UserDetails user = userDetailsService.loadUserByUsername(mobile);
        if (user == null){
            throw new InternalAuthenticationServiceException("无法获取用户信息") ;
        }
        SmsCodeAuthenticationToken token = new SmsCodeAuthenticationToken(user,user.getAuthorities()) ;
        token.setDetails(authentication.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}

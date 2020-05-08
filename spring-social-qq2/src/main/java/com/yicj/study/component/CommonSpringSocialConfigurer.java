package com.yicj.study.component;


import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

// 默认情况下SocialAuthenticationFilter只关注/auth/{providerId} ，
// 当配正确时，会尝试获取providerId，并执行一系列OAuth流程。
// 如果OAuth认证后的用户在本地用户数据库中无法找到，则认为这是一个需要注册用户，默认会跳到/signup这个路由下进行处理
public class CommonSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl ;
    //注册页面url
    private String signUpUrl ;

    public CommonSpringSocialConfigurer(String filterProcessesUrl, String signUpUrl){
        this.filterProcessesUrl = filterProcessesUrl ;
        this.signUpUrl = signUpUrl ;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter  = (SocialAuthenticationFilter) object ;
        //为SocialAuthenticationFilter设置自定义URL
        filter.setFilterProcessesUrl(filterProcessesUrl);
        filter.setSignupUrl(signUpUrl);
        return (T) filter ;
    }
}

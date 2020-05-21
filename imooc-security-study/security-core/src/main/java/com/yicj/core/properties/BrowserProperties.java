package com.yicj.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {
    /**
     * 登录页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上
     */
    private String signInPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
    //登录类型
    private LoginType loginType = LoginType.JOSN ;
    /**
     * 社交登录，如果需要用户注册，跳转的页面
     */
    private String signUpUrl = "/imooc-signUp.html";
    /**
     * session管理配置项
     */
    private SessionProperties session = new SessionProperties();
    /**
     * 退出成功时跳转的url，如果配置了，则跳到指定的url，如果没配置，则返回json数据。
     */
    private String signOutUrl;

}

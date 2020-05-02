package com.yicj.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {
    //如果用户没有配置loginPge页面，则返回标准的登录页
    private String loginPage ="/imooc-signIn";
    //登录类型
    private LoginType loginType = LoginType.JOSN ;
}

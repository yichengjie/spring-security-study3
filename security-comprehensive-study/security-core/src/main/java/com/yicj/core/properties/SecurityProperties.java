package com.yicj.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

//SecurityProperties会读取配置文件中所有以imooc.security开头的配置项
//其中以browser开头的配置都会读取到browser对象中去
@Data
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

    /**
     * 浏览器相关配置
     */
    private BrowserProperties browser = new BrowserProperties() ;

    /**
     * 验证码相关配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties() ;

}

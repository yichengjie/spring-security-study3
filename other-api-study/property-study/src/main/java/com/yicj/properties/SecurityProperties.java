package com.yicj.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

//SecurityProperties会读取配置文件中所有以imooc.security开头的配置项
//其中以browser开头的配置都会读取到browser对象中去
@Data
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties() ;
    private ValidateCodeProperties validate = new ValidateCodeProperties() ;

}

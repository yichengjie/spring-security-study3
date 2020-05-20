package com.yicj.core.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * ClassName: QQProperties
 * Description: TODO(描述)
 * Date: 2020/5/20 20:48
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class QQProperties extends SocialProperties {
    @Setter
    @Getter
    private String providerId = "qq" ;
}

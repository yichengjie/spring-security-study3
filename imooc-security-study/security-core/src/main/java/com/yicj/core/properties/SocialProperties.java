package com.yicj.core.properties;

import lombok.Data;

/**
 * ClassName: SocialProperties
 * Description: TODO(描述)
 * Date: 2020/5/20 20:50
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
public class SocialProperties {

    //社交登录默认拦截地址
    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties() ;
}

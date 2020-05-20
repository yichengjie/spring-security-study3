package com.yicj.core.social.support;

import lombok.Data;

/**
 * ClassName: SocialUserInfo
 * Description: TODO(描述)
 * Date: 2020/5/21 7:09
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
public class SocialUserInfo {

    private String providerId;

    private String providerUserId ;

    private String nickname ;

    private String headimg ;

}

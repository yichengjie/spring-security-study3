package com.yicj.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * ClassName: ImoocSpringSocialConfigurer
 * Description: TODO(描述)
 * Date: 2020/5/20 21:20
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class ImoocSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl ;

    public ImoocSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl ;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter)super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T)filter;
    }
}

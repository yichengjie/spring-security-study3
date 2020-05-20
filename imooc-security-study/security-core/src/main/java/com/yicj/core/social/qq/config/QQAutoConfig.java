package com.yicj.core.social.qq.config;

import com.yicj.core.properties.QQProperties;
import com.yicj.core.properties.SecurityProperties;
import com.yicj.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * ClassName: QQAutoConfig
 * Description: TODO(描述)
 * Date: 2020/5/20 20:52
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Configuration
@ConditionalOnProperty(prefix = "imooc.security.social.qq", name = "app-id")
public class QQAutoConfig  extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties ;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityProperties.getSocial().getQq();
        String providerId = qqProperties.getProviderId() ;
        String appId = qqProperties.getAppId() ;
        String appSecret = qqProperties.getAppSecret() ;
        return new QQConnectionFactory(providerId, appId, appSecret);
    }
}

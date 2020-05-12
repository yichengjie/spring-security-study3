package com.yicj.study.connect;

import com.yicj.study.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * 整合上面的这些部件，并提供一个简单的接口来创建连接
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId ,String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}

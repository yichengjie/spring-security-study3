package com.yicj.core.social.weixin.config;

import com.yicj.core.properties.SecurityProperties;
import com.yicj.core.properties.WeiXinProperties;
import com.yicj.core.social.weixin.connect.WeiXinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

// 微信登录配置
@Configuration
@ConditionalOnProperty(prefix = "imooc.security.social.weixin", name = "app-id")
public class WeixinAutoConfiguration extends SocialConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
									   Environment environment) {
		configurer.addConnectionFactory(createConnectionFactory());
	}

	protected ConnectionFactory<?> createConnectionFactory() {
		WeiXinProperties weiXinConfig = securityProperties.getSocial().getWeixin();

		return new WeiXinConnectionFactory(weiXinConfig.getProviderId(), weiXinConfig.getAppId(),
				weiXinConfig.getAppSecret());
	}

}

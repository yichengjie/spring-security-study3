package com.yicj.core.authorize.impl;

import com.yicj.core.authorize.AuthorizeConfigProvider;
import com.yicj.core.properties.SecurityConstants;
import com.yicj.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 */
@Component
@Order(Integer.MIN_VALUE)
public class ImoocAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		// 登录页面，和验证码页面不需要权限验证
	    config.antMatchers(
				SecurityConstants.DEFAULT_UNAUTHENTICATION_URL, // 表单登录地址
				SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, // 手机验证码登录地址
				SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID, // openId登录地址
				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
				securityProperties.getBrowser().getSignInPage(), //登录页面
				securityProperties.getBrowser().getSignUpUrl()) // 注册页面
			.permitAll() ;
		return false;
	}

}

package com.yicj.core.properties;

import lombok.Data;

/**
 * 微信登录配置项
 */
@Data
public class WeiXinProperties {
	//Application id.
	private String appId;
	//Application secret.
	private String appSecret;
	//第三方id，用来决定发起第三方登录的url，默认是 weixin。
	private String providerId = "weixin";
}

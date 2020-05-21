package com.yicj.core.social.weixin.connect;

import com.yicj.core.social.weixin.api.WeiXin;
import com.yicj.core.social.weixin.api.WeiXinImpl;
import com.yicj.core.social.weixin.connect.WeiXinOAuth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

//微信的OAuth2流程处理器的提供器，供spring social的connect体系调用
public class WeiXinServiceProvider extends AbstractOAuth2ServiceProvider<WeiXin> {
	
	//微信获取授权码的url
	private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
	//微信获取accessToken的url
	private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

	public WeiXinServiceProvider(String appId, String appSecret) {
		super(new WeiXinOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
	}

	@Override
	public WeiXin getApi(String accessToken) {
		return new WeiXinImpl(accessToken);
	}

}

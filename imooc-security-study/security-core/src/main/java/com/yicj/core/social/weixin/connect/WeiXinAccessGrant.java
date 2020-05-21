package com.yicj.core.social.weixin.connect;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.social.oauth2.AccessGrant;

/**
 * 微信的access_token信息。与标准OAuth2协议不同，微信在获取access_token时会同时返回openId,并没有单独的通过accessToke换取openId的服务
 * 所以在这里继承了标准AccessGrant，添加了openId字段，作为对微信access_token信息的封装。
 */
public class WeiXinAccessGrant extends AccessGrant {
	@Setter
	@Getter
	private String openId;
	
	public WeiXinAccessGrant() {
		super("");
	}

	public WeiXinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
		super(accessToken, scope, refreshToken, expiresIn);
	}
}

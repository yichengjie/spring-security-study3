package com.yicj.core.social.weixin.api;

import com.yicj.core.social.weixin.model.WeiXinUserInfo;

//微信API调用接口
public interface WeiXin {

	WeiXinUserInfo getUserInfo(String openId);
}
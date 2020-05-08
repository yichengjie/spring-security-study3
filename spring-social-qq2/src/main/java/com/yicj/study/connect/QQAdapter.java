package com.yicj.study.connect;

import com.yicj.study.api.QQ;
import com.yicj.study.model.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 将服务提供商的api映射为统一的连接模型
 */
public class QQAdapter implements ApiAdapter<QQ> {

    @Override
    public boolean test(QQ api) {
        return true;
    }

    //通过api获取用户信息，并填充到Connection对象中
    @Override
    public void setConnectionValues(QQ qq, ConnectionValues values) {
        QQUserInfo userInfo =  qq.getUserInfo() ;
        values.setProviderUserId(userInfo.getOpenId());
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getQqFigureUrl100());
        values.setProfileUrl(null);
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
    }
}

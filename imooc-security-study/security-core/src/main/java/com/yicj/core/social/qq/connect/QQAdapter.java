package com.yicj.core.social.qq.connect;

import com.yicj.core.social.qq.api.QQ;
import com.yicj.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * ClassName: QQAdapter
 * Description: TODO(描述)
 * Date: 2020/5/20 20:32
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();

        values.setProviderUserId(userInfo.getOpenId());
        //
        values.setDisplayName(userInfo.getNickname());
        //qq空间图像
        values.setProfileUrl(userInfo.getFigureurl30());
        //qq图像
        values.setImageUrl(userInfo.getQqFigureUrl40());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}

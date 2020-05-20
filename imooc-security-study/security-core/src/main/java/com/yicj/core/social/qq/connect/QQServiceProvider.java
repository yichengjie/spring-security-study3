package com.yicj.core.social.qq.connect;

import com.yicj.core.social.qq.api.QQ;
import com.yicj.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * ClassName: QQServiceProvider
 * Description: TODO(描述)
 * Date: 2020/5/20 20:23
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize" ;
    private static final String URL_ACCESSTOKEN = "https://graph.qq.com/oauth2.0/token";

    private String appId ;

    public QQServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESSTOKEN));
        this.appId = appId ;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}

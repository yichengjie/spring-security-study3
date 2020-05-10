package com.yicj.study.api.impl;

import com.yicj.study.api.QQ;
import com.yicj.study.component.JacksonFromTextHtmlMessageConverter;
import com.yicj.study.model.QQUserInfo;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.web.client.RestTemplate;

/**
 * 继承AbstractOAuth1ApiBinding，完成api接口与spring social的绑定
 */
public class QQImpl extends AbstractOAuth1ApiBinding implements QQ {

    //获取openId的api
    private static final String QQ_URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token={accessToken}";
    //获取用户信息的api (appId也就是client_id)
    private static final String QQ_URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?access_token={accessToken}&oauth_consumer_key={appId}&openid={openId}";

    private String appId ; //client_id
    private String openId ; // openId
    private String accessToken ;

    //构造函数在accessToken获取之后被调用，此处可以初始化openId
    public QQImpl(String accessToken, String appId){
        // access_token 作为查询参数被携带
        // 获取用户信息接口，无需申明携带accessToken,所有资源交互接口都会自行携带
        //super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER) ;
        this.appId = appId ;
        this.accessToken = accessToken ;
        //获取openId接口响应
        String result = getRestTemplate().getForObject(QQ_URL_GET_OPENID, String.class, accessToken) ;
        //提取openId
        this.openId = result.substring(result.lastIndexOf(":\"")+ 2, result.indexOf("\"}"))  ;

    }

    @Override
    public QQUserInfo getUserInfo() {
        QQUserInfo qqUserInfo = getRestTemplate().getForObject(QQ_URL_GET_USER_INFO, QQUserInfo.class,accessToken, appId, openId) ;
        //为用户信息补充openId
        if (qqUserInfo != null){
            qqUserInfo.setOpenId(openId);
        }
        return qqUserInfo;
    }

    @Override
    protected void configureRestTemplate(RestTemplate restTemplate) {
        // 为api交互的resultTemplate实例添加text/html转json对象的支持
        restTemplate.getMessageConverters().add(new JacksonFromTextHtmlMessageConverter()) ;
    }
}

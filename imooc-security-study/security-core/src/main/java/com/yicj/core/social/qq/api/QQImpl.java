package com.yicj.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yicj.core.social.qq.model.QQUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * ClassName: QQImpl
 * Description: TODO(描述)
 * Date: 2020/5/20 19:42
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    //private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?access_token=%s&oauth_consumer_key=%s&openid=%s" ;
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s" ;
    private ObjectMapper objectMapper = new ObjectMapper() ;
    private String appId ;
    private String openId ;


    public QQImpl(String accessToken, String appId){
        super(accessToken, TokenStrategy.OAUTH_TOKEN_PARAMETER) ;
        this.appId = appId ;
        String url = String.format(URL_GET_OPENID,accessToken) ;
        String result = this.getRestTemplate().getForObject(url, String.class);
        log.info("response :{}",result);
        //callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        this.openId = StringUtils.substringBetween(result,"\"openid\":\"", "\"}") ;
    }


    @Override
    public QQUserInfo getUserInfo()  {
        try {
            String url = String.format(URL_GET_USERINFO,appId,openId) ;
            String result = getRestTemplate().getForObject(url, String.class);
            log.info("response : {}", result);
            QQUserInfo qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
            return qqUserInfo ;
        }catch (IOException e){
            throw new RuntimeException(e) ;
        }
    }

    public static void main(String[] args) {
        String str = "callback( {\"client_id\":\"YOUR_APPID\",\"openid\":\"YOUR_OPENID\"} );" ;
        //String openId = StringUtils.substringBetween(str, "\"openid\":\"", "\"}");
        String openId = StringUtils.substringBetween(str, "\"openid\":", "}");
        System.out.println(openId);
    }
}

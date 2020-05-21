package com.yicj.core.social.qq.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * ClassName: QQUserInfo
 * Description: TODO(描述)
 * Date: 2020/5/20 19:43
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QQUserInfo {

    private String nickname ;
    //大小为30*30像素的QQ空间头像url
    @JsonProperty("figureurl")
    private String figureurl30 ;
    //大小为50*50像素的QQ空间头像url
    @JsonProperty("figureurl_1")
    private String figureurl50 ;
    //大小为100*100像素的QQ空间头像url
    @JsonProperty("figureurl_2")
    private String figureurl100 ;

    private String gender ;
    //大小为40*40像素的QQ头像url
    @JsonProperty("figureurl_qq_1")
    private String qqFigureUrl40 ;
    //大小为100*100像素的QQ头像url
    @JsonProperty("figureurl_qq_2")
    private String qqFigureUrl100 ;

    //携带openId备用
    private String openId ;
}

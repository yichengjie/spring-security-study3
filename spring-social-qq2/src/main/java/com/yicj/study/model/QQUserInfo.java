package com.yicj.study.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 对应get_user_info接口的数据形式
 */
@Data
public class QQUserInfo {
    private String nickname ;
    @JsonProperty("figureurl")
    private String figureurl30 ;
    @JsonProperty("figureurl_1")
    private String figureurl50 ;
    @JsonProperty("figureurl_2")
    private String figureurl100 ;
    private String gender ;
    @JsonProperty("figureurl_qq_1")
    private String qqFigureUrl40 ;
    @JsonProperty("figureurl_qq_2")
    private String qqFigureUrl100 ;
    //携带openId备用
    private String openId ;
}

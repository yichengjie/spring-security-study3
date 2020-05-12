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
    /**
     * {
     * 	"ret": 0,
     * 	"msg": "",
     * 	"is_lost": 0,
     * 	"nickname": "易成杰",
     * 	"gender": "男",
     * 	"gender_type": 1,
     * 	"province": "北京",
     * 	"city": "顺义",
     * 	"year": "1988",
     * 	"constellation": "",
     * 	"figureurl": "http://qzapp.qlogo.cn/qzapp/100550231/A1DC7A0D50010C510BE686794EE766DA/30",
     * 	"figureurl_1": "http://qzapp.qlogo.cn/qzapp/100550231/A1DC7A0D50010C510BE686794EE766DA/50",
     * 	"figureurl_2": "http://qzapp.qlogo.cn/qzapp/100550231/A1DC7A0D50010C510BE686794EE766DA/100",
     * 	"figureurl_qq_1": "http://thirdqq.qlogo.cn/g?b=oidb&k=y45wo4PH95kJZ8T9ZPkEiaw&s=40&t=1555761628",
     * 	"figureurl_qq_2": "http://thirdqq.qlogo.cn/g?b=oidb&k=y45wo4PH95kJZ8T9ZPkEiaw&s=100&t=1555761628",
     * 	"figureurl_qq": "http://thirdqq.qlogo.cn/g?b=oidb&k=y45wo4PH95kJZ8T9ZPkEiaw&s=640&t=1555761628",
     * 	"figureurl_type": "1",
     * 	"is_yellow_vip": "0",
     * 	"vip": "0",
     * 	"yellow_vip_level": "0",
     * 	"level": "0",
     * 	"is_yellow_year_vip": "0"
     * }
     */
}

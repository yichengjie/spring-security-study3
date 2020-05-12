package com.yicj.core.model;

import lombok.Data;

@Data
public class ReturnData {
    private StatusCode code ;
    private Object data ;
    private String msg ;

    public ReturnData(StatusCode code, Object data, String msg){
        this.code = code ;
        this.data = data ;
        this.msg = msg ;
    }
}

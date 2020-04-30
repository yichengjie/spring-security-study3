package com.yicj.redis.exception;

import com.yicj.redis.model.StatusCode;

public class MyException extends RuntimeException {
    private StatusCode code ;

    public MyException(StatusCode code, String msg){
        super(msg);
        this.code = code ;
    }
}

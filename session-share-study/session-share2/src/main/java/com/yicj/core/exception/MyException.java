package com.yicj.core.exception;

import com.yicj.core.model.StatusCode;

public class MyException extends RuntimeException {
    private StatusCode code ;

    public MyException(StatusCode code, String msg){
        super(msg);
        this.code = code ;
    }
}

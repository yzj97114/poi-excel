package com.zzzsj.common.exception;


/**
 * @author 79282
 * 异常拦截
 */
public class CommonException extends RuntimeException {
    protected Integer code;

    public Integer getCode() {
        return code;
    }

    protected CommonException(Integer code, String message, Exception e) {
        super(message, e);
        this.code = code;
    }

    protected CommonException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    protected CommonException(Exception e) {
        super(e);
    }
}

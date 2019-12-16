package com.zzzsj.common.exception;


/**
 * @author 79282
 * 系统异常拦截
 */
public class SystemException extends CommonException {
    public SystemException(Integer code, String message, Exception e) {
        super(code, message, e);
    }

    public SystemException(Exception e) {
        super(e);
    }
}

package com.zzzsj.common.exception;


/**
 * @author 79282
 * 业务异常拦截
 */
public class BizException extends CommonException {
    public BizException(Integer code, String message) {
        super(code, message);
    }

    public BizException(Integer code, String message, Exception e) {
        super(code, message, e);
    }
}

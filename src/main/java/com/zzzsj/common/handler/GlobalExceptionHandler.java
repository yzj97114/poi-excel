//package com.zzzsj.common.handler;
//
//import com.zzzsj.common.enums.ErrorCode;
//import com.zzzsj.common.exception.BizException;
//import com.zzzsj.common.exception.SystemException;
//import com.zzzsj.common.utils.JsonResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.thymeleaf.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author 79282
// * 全局异常拦截
// */
//@Slf4j
//@ControllerAdvice
//public class GlobalExceptionHandler implements ExcetionHandler {
//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public JsonResult otherExceptionHandler(HttpServletRequest request, Exception e) {
//        JsonResult result = new JsonResult();
//        result.setCode(ErrorCode.SYS_EXCEPTION.getCode());
//        result.setMessage(ErrorCode.SYS_EXCEPTION.getMsg());
//        log.error("==>其他异常" + e.getMessage(), e);
//        return result;
//    }
//
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    @ResponseBody
//    public JsonResult argumentValidHandler(HttpServletRequest req, Exception e) {
//
//        JsonResult result = new JsonResult();
//        List<String> errorList = new ArrayList<>();
//        List<ObjectError> allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
//        for (ObjectError error : allErrors) {
//            errorList.add(error.getDefaultMessage());
//        }
//        result.setCode(ErrorCode.ARGUMENT_NOT_VALID.getCode());
//        result.setMessage(StringUtils.join(errorList, "<br/>"));
//        return result;
//    }
//
//    @ExceptionHandler(value = {BizException.class, SystemException.class, IllegalArgumentException.class})
//    @ResponseBody
//    public JsonResult businessExceptionHandler(HttpServletRequest req, Exception e) {
//        JsonResult result = new JsonResult();
//        if (e instanceof BizException) {
//            BizException biz = (BizException) e;
//            result.setMessage(biz.getMessage());
//            result.setCode(biz.getCode());
//            log.error("==>业务错误代码:{},错误消息:{}", biz.getCode(), biz.getMessage());
//        }
//        if (e instanceof SystemException) {
//            SystemException systemException = (SystemException) e;
//            result.setMessage(systemException.getMessage());
//            result.setCode(systemException.getCode());
//            log.error("==>程序错误代码:{},错误消息:{}", systemException.getCode(), systemException.getMessage());
//        }
//        if (e instanceof IllegalArgumentException) {
//            IllegalArgumentException illegalArgumentException = (IllegalArgumentException) e;
//            result.setMessage(illegalArgumentException.getMessage());
//            result.setCode(ErrorCode.ILLEGAL_ARGUMENT.getCode());
//            log.error("==>参数错误代码:{},错误消息:{}", ErrorCode.ILLEGAL_ARGUMENT.getCode(), illegalArgumentException.getMessage());
//        }
//        return result;
//    }
//
//}

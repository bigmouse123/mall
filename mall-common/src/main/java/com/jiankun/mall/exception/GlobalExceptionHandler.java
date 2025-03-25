package com.jiankun.mall.exception;

import com.jiankun.mall.util.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/25 13:55
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

    //自定义异常处理
    @ExceptionHandler(JKException.class)
    @ResponseBody
    public Result error(JKException e) {
        return Result.error(e.getMessage());
    }
}
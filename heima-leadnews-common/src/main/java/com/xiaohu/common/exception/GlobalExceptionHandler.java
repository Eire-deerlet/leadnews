package com.xiaohu.common.exception;

import com.xiaohu.common.dtos.ResponseResult;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** 全局异常处理类
 * @author Violet
 */
@RestControllerAdvice //标记为异常拦截器
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(value = LeadNewsException.class)
    public ResponseResult handlerLeadNewsException(LeadNewsException e){
        return ResponseResult.errorResult(e.getStatus(),e.getMessage());
    }

    /**
     * 处理系统异常
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult handlerException(Exception e){

        return ResponseResult.errorResult(500,"服务异常"+e.getMessage());
    }


}

package com.xiaohu.common.exception;

import com.xiaohu.common.dtos.AppHttpCodeEnum;
import lombok.Getter;

/** 自定义业务异常
 * @author Violet
 */
@Getter
public class LeadNewsException extends RuntimeException{
    //状态码
    private Integer status;

    public LeadNewsException(Integer status,String message){

        super(message);
        this.status=status;

    }

    public  LeadNewsException(AppHttpCodeEnum codeEnum){

        super(codeEnum.getErrorMessage());
        this.status=codeEnum.getCode();

    }
}

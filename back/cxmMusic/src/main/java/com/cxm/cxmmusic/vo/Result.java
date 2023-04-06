package com.cxm.cxmmusic.vo;

import com.cxm.cxmmusic.exception.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @create 2023-03-18 16:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result <T>{
    private Integer code;
    private String msg;
    private T data;

    public Result(StatusCodeEnum statusCodeEnum){
        this(statusCodeEnum.getCode(), statusCodeEnum.getMessage(), null);
    }

    public Result(StatusCodeEnum statusCodeEnum,T data){
        this(statusCodeEnum.getCode(), statusCodeEnum.getMessage(), data);
    }

}

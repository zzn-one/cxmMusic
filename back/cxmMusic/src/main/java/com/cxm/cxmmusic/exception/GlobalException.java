package com.cxm.cxmmusic.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局异常处理
 * @create 2023-03-18 22:50
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GlobalException extends RuntimeException {
    private StatusCodeEnum statusCodeEnum;
}

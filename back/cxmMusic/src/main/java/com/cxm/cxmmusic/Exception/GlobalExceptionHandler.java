package com.cxm.cxmmusic.Exception;

import com.auth0.jwt.exceptions.*;

import com.cxm.cxmmusic.vo.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author zzn
 * @create 2022-11-10 9:31
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

//    /*jwt异常*/
    @ExceptionHandler(TokenExpiredException.class)
    public Result<String> handleTokenExpiredException() {
        return new Result<>(StatusCodeEnum.UNAUTHORIZED, null);
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public Result<String> handleSignatureVerificationException() {
        return new Result<>(StatusCodeEnum.UNAUTHORIZED, null);
    }

    @ExceptionHandler(AlgorithmMismatchException.class)
    public Result<String> handleAlgorithmMismatchException() {
        return new Result<>(StatusCodeEnum.UNAUTHORIZED, null);
    }

    @ExceptionHandler(InvalidClaimException.class)
    public Result<String> handleInvalidClaimException() {
        return new Result<>(StatusCodeEnum.UNAUTHORIZED, null);
    }




    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {

        if (e instanceof GlobalException) {
            //处理自定义的异常
            GlobalException ex = (GlobalException) e;
            return new Result<>(ex.getStatusCodeEnum(), null);

        } else if (e instanceof JWTVerificationException) {
            //处理jwt认证异常
            return new Result<>(StatusCodeEnum.UNAUTHORIZED, null);

        }
        else if (e instanceof BadCredentialsException) {
            //处理  用户账号认证异常 （用户名或密码错误）
            return new Result<>(StatusCodeEnum.ACCOUNT_UNMATCHED_PASSWORD, null);

        } else if (e instanceof InternalAuthenticationServiceException) {
            //处理 用户账号认证异常（账号不存在）
            if (e.getMessage().equals(StatusCodeEnum.NO_ACCOUNT.getMessage())) {
                return new Result<>(StatusCodeEnum.NO_ACCOUNT, null);
            }
            //todo 处理其他原因导致的异常

        }
        e.printStackTrace();
        return new Result<>(StatusCodeEnum.EXCEPTION, null);
    }


}

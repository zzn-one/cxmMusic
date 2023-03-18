package com.cxm.cxmmusic.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
/*
* 自定义状态码
*
* */
public enum StatusCodeEnum {
    // 通用
    OK(200, "OK"),
    ERROR(500, "服务器错误"),
    EXCEPTION(10000,"系统发生异常"),
    FORBIDDEN(403, "您的访问权限不足！"),
    UNAUTHORIZED(401, "身份认证失败，请重新登录！"),

    // 登录模块
    ACCOUNT_UNMATCHED_PASSWORD(10001,"账号或密码错误！"),
    NO_ACCOUNT(10002,"账号不存在");
    // 注册模块
    private final Integer code;
    private final String message;
}
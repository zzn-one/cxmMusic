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
    EXCEPTION(10000, "系统发生异常"),

    //token
    FORBIDDEN(403, "您的访问权限不足！"),
    UNAUTHORIZED(401, "身份认证失败，请重新登录！"),
    //TOKEN_EXPIRED(100401,"身份认证过期"),

    // 登录模块
    ACCOUNT_UNMATCHED_PASSWORD(10001, "账号或密码错误！"),
    NO_ACCOUNT(10002, "账号不存在"),
    NOT_LOGGED_IN(10003, "账号未登录"),
    // 注册模块
    DIFFERENT_PASSWORD(10011, "两次输入的密码不一致"),
    REGISTER_ERROR(10012, "注册失败"),

    //用户模块
    NO_USER(10021, "系统无该用户的信息"),
    DIFFERENT_OLD_PASSWORD(10022, "两次输入的旧密码不一致"),
    DIFFERENT_NEW_PASSWORD(10023, "两次输入的新密码不一致"),
    SAME_NEW_OLD_PASSWORD(10024, "新旧密码不能相同"),
    PASSWORD_ERROR(10025, "旧密码错误");

    private final Integer code;
    private final String message;
}
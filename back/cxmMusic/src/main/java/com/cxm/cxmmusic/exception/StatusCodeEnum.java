package com.cxm.cxmmusic.exception;

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
    PASSWORD_ERROR(10025, "旧密码错误"),

    //歌曲播放量模块
    ERROR_UPDATE_SONG_PLAY_NUMBER(10031, "歌曲的播放量修改出错！"),
    ERROR_UPDATE_USER_PLAY_SONG_TIMES(10032, "用户的歌曲播放次数修改出错！"),

    //播放列表模块
    ERROR_UPDATE_PLAY_INDEX(10041, "播放索引更新出错"),
    ERROR_CLEAR_SONG(10042, "播放列表歌曲删除失败！"),
    ERROR_GET_PLAYLIST(10043, "获取播放列表失败"),

    //歌手模块
    ERROR_GET_SINGER(10051,"获取歌手信息失败"),
    ERROR_UPLOAD_AVATAR(10052,"上传歌手图片失败"),
    ERROR_ADD_SINGER(10053,"新增歌手失败"),

    //歌单模块
    ERROR_NEW_SONGLIST(10061,"新增歌单失败"),
    ERROR_EDIT_SONGLIST(10062,"修改歌单信息失败"),
    ERROR_DELETE_SONGS(10063,"移除部分歌曲失败");

    private final Integer code;
    private final String message;
}
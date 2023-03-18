package com.cxm.cxmmusic.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号昵称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 头像的url
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 性别  外键
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 个性签名
     */
    @TableField(value = "signature")
    private String signature;

    /**
     * 注册时间
     */
    @TableField(value = "register_time")
    private Date registerTime;

    /**
     * 最近登录时间
     */
    @TableField(value = "lasted_login_time")
    private Date lastedLoginTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    private Integer deleted;

    /**
     * 
     */
    @TableField(value = "version")
    @Version

    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
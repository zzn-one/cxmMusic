package com.cxm.cxmmusic.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data

@ApiModel("用户实体")
public class User implements Serializable {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @ApiModelProperty("账号昵称")
    @TableField(value = "name")
    private String name;


    @ApiModelProperty("账号")
    @TableField(value = "account")
    private String account;


    @ApiModelProperty("密码")
    @TableField(value = "password")
    private String password;


    @ApiModelProperty("头像的url")
    @TableField(value = "avatar_url")
    private String avatarUrl;

    @ApiModelProperty("性别")
    @TableField(value = "gender")
    private String gender;


    @ApiModelProperty("个性签名")
    @TableField(value = "signature")
    private String signature;


    @ApiModelProperty("注册时间")
    @TableField(value = "register_time")
    private Date registerTime;


    @ApiModelProperty("最近登录时间")
    @TableField(value = "lasted_login_time")
    private Date lastedLoginTime;


    @ApiModelProperty("逻辑删除")
    @TableField(value = "deleted")
    private Integer deleted;

    @TableField(value = "version")
    @Version
    private Integer version;

    @ApiModelProperty("用户类型")
    @TableField(value = "type")
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
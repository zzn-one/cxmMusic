package com.cxm.cxmmusic.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @TableName singer
 */
@TableName(value ="singer")
@Data
@ApiModel
public class Singer implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("主键")
    private Integer id;


    @ApiModelProperty("歌手名称")
    @TableField(value = "name")
    private String name;


    @ApiModelProperty("性别")
    @TableField(value = "gender")
    private String gender;


    @ApiModelProperty("歌手类型")
    @TableField(value = "type")
    private String type;


    @ApiModelProperty("头像url")
    @TableField(value = "avatar_url")
    private String avatarUrl;


    @ApiModelProperty("歌曲数量")
    @TableField(value = "song_number")
    private Integer songNumber;


    @ApiModelProperty("歌手简介")
    @TableField(value = "introduction")
    private String introduction;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private Date createTime;


    @ApiModelProperty("逻辑删除")
    @TableField(value = "deleted")
    private Integer deleted;


    @ApiModelProperty("乐观锁")
    @TableField(value = "version")
    @Version

    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
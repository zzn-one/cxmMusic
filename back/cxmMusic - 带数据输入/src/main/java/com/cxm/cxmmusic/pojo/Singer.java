package com.cxm.cxmmusic.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName singer
 */
@TableName(value ="singer")
@Data
public class Singer implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌手名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 性别 外键
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 类型  单人（男/女） 或 组合  外键
     */
    @TableField(value = "type")
    private String type;

    /**
     * 头像url
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 歌曲数量
     */
    @TableField(value = "song_number")
    private Integer songNumber;

    /**
     * 歌手简介
     */
    @TableField(value = "introduction")
    private String introduction;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    private Integer deleted;

    /**
     * 乐观锁
     */
    @TableField(value = "version")
    @Version

    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
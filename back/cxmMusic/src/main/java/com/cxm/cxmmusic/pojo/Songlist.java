package com.cxm.cxmmusic.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName songlist
 */
@TableName(value ="songlist")
@Data
public class Songlist implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 作者名称
     */
    @TableField(value = "author_name")
    private String authorName;

    /**
     * 作者的账号  外键
     */
    @TableField(value = "author_account")
    private String authorAccount;

    /**
     * 标签  外键
     */
    @TableField(value = "tag")
    private String tag;

    /**
     * 歌单封面
     */
    @TableField(value = "img_url")
    private String imgUrl;

    /**
     * 简介
     */
    @TableField(value = "introduction")
    private String introduction;

    /**
     * 歌单的歌曲数量
     */
    @TableField(value = "song_number")
    private Integer songNumber;

    /**
     * 播放量
     */
    @TableField(value = "play_number")
    private Long playNumber;

    /**
     * 收藏量
     */
    @TableField(value = "star_number")
    private Long starNumber;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    private Date createdTime;

    /**
     * 逻辑删除 0删除 1未被删除
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
package com.cxm.cxmmusic.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName song
 */
@TableName(value ="song")
@Data
public class Song implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌曲名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 歌曲封面
     */
    @TableField(value = "img_url")
    private String imgUrl;

    /**
     * 歌曲时长（毫秒）
     */
    @TableField(value = "duration")
    private Long duration;

    /**
     * 歌曲资源路径
     */
    @TableField(value = "source_url")
    private String sourceUrl;

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
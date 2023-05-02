package com.cxm.cxmmusic.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @TableName song
 */
@TableName(value ="song")
@Data
@ApiModel("歌曲实体")
public class Song implements Serializable {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @ApiModelProperty("歌曲名称")
    @TableField(value = "name")
    private String name;


    @ApiModelProperty("歌曲封面")
    @TableField(value = "img_url")
    private String imgUrl;


    @ApiModelProperty("歌曲时长（毫秒）")
    @TableField(value = "duration")
    private Long duration;


    @ApiModelProperty("歌曲资源路径")
    @TableField(value = "source_url")
    private String sourceUrl;


    @ApiModelProperty("播放量")
    @TableField(value = "play_number")
    private Long playNumber;


    @ApiModelProperty("收藏量")
    @TableField(value = "star_number")
    private Long starNumber;


    @ApiModelProperty("逻辑删除 ")
    @TableField(value = "deleted")
    private Integer deleted;


    @ApiModelProperty("乐观锁")
    @TableField(value = "version")
    @Version
    private Integer version;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private Date createTime;




}
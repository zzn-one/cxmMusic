package com.cxm.cxmmusic.vo.mongo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * @TableName user_play_song
 */
@ApiModel("用户与歌曲的播放关系")
@Document("userPlaySong")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPlaySong implements Serializable {
    /**
     * 用户账号
     */
    @Field()
    @ApiModelProperty("用户账号")
    private String account;

    /**
     * 歌曲id
     */
    @Field()
    @ApiModelProperty("歌曲id")
    private Integer songId;

    /**
     * 用户播放该歌曲的次数
     */
    @Field()
    @ApiModelProperty("用户播放该歌曲的次数")
    private Long times;

    /**
     * 用户最近一次播放该歌曲的时间
     */
    @Field()
    @ApiModelProperty("用户最近一次播放该歌曲的时间")
    private Date lastedPlayTime;

    public void addTimes() {
        this.times += 1;
    }
    public void addTimes(Integer times) {
        this.times += times;
    }

}
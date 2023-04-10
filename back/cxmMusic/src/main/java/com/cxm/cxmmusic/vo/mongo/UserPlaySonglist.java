package com.cxm.cxmmusic.vo.mongo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;


@Document(value ="userPlaySongList")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPlaySonglist implements Serializable {
    /**
     * 用户账号
     */
    @Field()
    @ApiModelProperty("用户账号")
    private String account;

    /**
     * 歌单id
     */
    @Field()
    @ApiModelProperty("歌单id")
    private Integer songlistId;

    /**
     * 用户播放该歌单的次数
     */
    @Field()
    @ApiModelProperty("播放次数")
    private Long times;

    @Field()
    @ApiModelProperty("最近播放时间")
    private Date lastedPlayTime;


    public void addTimes(){
        this.times += 1;
    }

}
package com.cxm.cxmmusic.vo.mongo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author
 * @create 2023-03-29 13:23
 */
@ApiModel("歌曲的播放量")
@Document("songPlayNumber")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SongPlayNumber {

    @Id
    @ApiModelProperty("歌曲id")
    private Integer songId;
    @Field
    @ApiModelProperty("播放量")
    private Long times;


    public void addTimes() {
        this.times += 1;
    }
    public void addTimes(Integer times) {
        this.times += times;
    }

    public SongPlayNumber(Integer songId){
        this.songId = songId;
        this.times = 0L;
    }
}

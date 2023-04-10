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
 * @create 2023-04-10 18:20
 */

@ApiModel("歌单的收藏量")
@Document("songlistStarNumber")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SonglistStarNumber {

    @Id
    @ApiModelProperty("歌曲id")
    private Integer songlistId;
    @Field
    @ApiModelProperty("收藏量")
    private Long times;


    public void addTimes() {
        this.times += 1;
    }
    public void addTimes(Integer times) {
        this.times += times;
    }

    public SonglistStarNumber(Integer songlistId){
        this.songlistId = songlistId;
        this.times = 0L;
    }
}

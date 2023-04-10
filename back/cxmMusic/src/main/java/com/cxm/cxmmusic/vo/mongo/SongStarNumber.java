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
@ApiModel("歌曲的收藏量")
@Document("songStarNumber")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SongStarNumber {

    @Id
    @ApiModelProperty("歌曲id")
    private Integer songId;
    @Field
    @ApiModelProperty("收藏量")
    private Long times;


    public void addTimes() {
        this.times += 1;
    }
    public void addTimes(Long times) {
        this.times += times;
    }

    public SongStarNumber(Integer songId){
        this.songId = songId;
        this.times = 0L;
    }
}

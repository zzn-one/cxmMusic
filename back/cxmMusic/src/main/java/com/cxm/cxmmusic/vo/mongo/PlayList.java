package com.cxm.cxmmusic.vo.mongo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2023-03-28 18:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "playList")
@ApiModel("播放列表")
public class PlayList {
    @Id
    @ApiModelProperty("账号")
    private String account;
    @Field
    @ApiModelProperty("播放索引")
    private Integer currentIndex;

    @Field
    @ApiModelProperty("歌曲列表")
    private List<PlaySong> songList;



    public PlayList(String account,List<PlaySong> songList){
        this.account = account;
        this.songList = songList;
        this.currentIndex = 0;
    }
}

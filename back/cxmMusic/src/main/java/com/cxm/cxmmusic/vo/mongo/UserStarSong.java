package com.cxm.cxmmusic.vo.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;


@Document("userStarSong")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserStarSong implements Serializable {
    /**
     * 用户id
     */
    @Field()
    private String account;

    /**
     * 歌曲id
     */
    @Field()
    private Integer songId;

}
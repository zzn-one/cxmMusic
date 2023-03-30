package com.cxm.cxmmusic.vo.mongo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * @TableName user_star_song
 */
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
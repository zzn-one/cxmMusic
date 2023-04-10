package com.cxm.cxmmusic.vo.mongo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("userStarSongList")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserStarSonglist implements Serializable {
    /**
     * 用户id
     */
    @Field()
    private String account;

    /**
     * 歌单id
     */
    @Field()
    private Integer songlistId;
}
package com.cxm.cxmmusic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author
 * @create 2023-03-28 18:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "playList")
public class PlayList {
    @Id
    private String account;
    @Field
    private List<PlaySong> playSongList;
}

package com.cxm.cxmmusic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author
 * @create 2023-03-28 16:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaySong {
    private Integer id;
    private String name;
    private String singerName;
    private Integer duration;
    private String sourceUrl;
}

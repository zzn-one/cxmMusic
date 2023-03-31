package com.cxm.cxmmusic.vo.mongo;

import com.cxm.cxmmusic.pojo.Singer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<Singer> singerList;
    private Integer duration;
    private String sourceUrl;
}

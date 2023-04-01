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
    //歌曲id
    private Integer id;
    //歌名
    private String name;
    //歌手列表
    private List<Singer> singerList;
    //歌曲时长
    private Integer duration;
    //歌曲资源路径
    private String sourceUrl;
    //歌曲封面路径
    private String imgUrl;
}

package com.cxm.cxmmusic.vo;

import com.cxm.cxmmusic.pojo.DictTag;
import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.pojo.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 歌曲的全部信息实体
 * @create 2023-03-30 13:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongAllMsg {
    private Song song;
    private List<Singer> singerList;
    private List<DictTag> tagList;
}

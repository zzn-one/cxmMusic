package com.cxm.cxmmusic.vo.mongo;

import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.pojo.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 歌曲的播放记录实体
 * @create 2023-03-30 13:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorySong {
    private Song song;
    private Date time;
    private List<Singer> singerList;
}

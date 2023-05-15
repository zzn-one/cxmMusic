package com.cxm.cxmmusic.myRecommand;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author
 * @create 2023-05-15 3:38
 */
@AllArgsConstructor
@Data
public class SongTimes implements Comparable<SongTimes> {

    /*
    * 歌曲id
    * */
    private Integer songId;

    /*
    * 用户播放该歌曲的次数 作为推荐的指标
    * */
    private Long times;


    @Override
    public int compareTo(SongTimes o) {
        return times > o.getTimes() ? -1 : 1;
    }
}

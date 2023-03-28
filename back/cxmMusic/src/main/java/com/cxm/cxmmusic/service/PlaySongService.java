package com.cxm.cxmmusic.service;

import com.cxm.cxmmusic.vo.PlaySong;

/**
 * @author
 * @create 2023-03-28 17:54
 */
public interface PlaySongService {
    /*
     * 根据歌曲id获取 PlaySong对象
     * */
    PlaySong getBySongId(Integer songId);
}

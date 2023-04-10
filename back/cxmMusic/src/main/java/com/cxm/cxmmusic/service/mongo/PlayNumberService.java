package com.cxm.cxmmusic.service.mongo;

/**
 * @author
 * @create 2023-03-29 18:28
 */
public interface PlayNumberService {
    /*
     * 歌曲播放量  增加 1
     * */
    void addSongPlayNumber(Integer songId);

    /*
     * 用户的播放记录
     *
     * 用户与歌曲id关联 且播放次数+1  记录最新的播放该歌曲的时间
     * */
    void addUserPlaySongNumber(String account, Integer songId);


    /*
     * 歌单播放量  增加 1
     * */
    void addSonglistPlayNumber(Integer songlistId);

}

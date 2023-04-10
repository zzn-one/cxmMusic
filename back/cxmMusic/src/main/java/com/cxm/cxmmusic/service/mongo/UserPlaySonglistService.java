package com.cxm.cxmmusic.service.mongo;


/**
 * 用户的歌单播放记录服务
 */
public interface UserPlaySonglistService {

    /*
     * 产生/增加 用户播放歌单 的记录
     * */
    void add(String account, Integer songlistId);

}

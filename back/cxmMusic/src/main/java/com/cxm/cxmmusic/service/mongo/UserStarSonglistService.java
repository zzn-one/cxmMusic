package com.cxm.cxmmusic.service.mongo;


import com.cxm.cxmmusic.pojo.Songlist;

import java.util.List;

public interface UserStarSonglistService {

    /*
     * 用户收藏歌单
     * */
    void starSonglist(String account, Integer songlistId);

    /*
     * 用户取消收藏歌单
     * */
    void starCancelSonglist(String account, Integer songlistId);

    /*
     * 获取收藏的歌单列表 根据用户账号
     * */
    List<Songlist> getSonglists(String account);

    /*
     * 改变歌单的收藏数量 +1或-1
     * */
    void changeSonglistStarNumber(Integer songId,Long times);

    /*
     * 歌单是否已经当前用户收藏
     * */
    Boolean stared(String account, Integer songlistId);
}

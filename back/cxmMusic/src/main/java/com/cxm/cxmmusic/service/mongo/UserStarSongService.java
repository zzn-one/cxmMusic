package com.cxm.cxmmusic.service.mongo;

import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.vo.mongo.HistorySong;
import com.cxm.cxmmusic.vo.mongo.UserStarSong;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface UserStarSongService  {

    /*
     * 用户收藏歌曲
     * */
    void starSong(String account, List<Song> songs);

    /*
     * 用户取消收藏歌曲
     * */
    void starCancelSong(String account, List<Song> songs);

    /*
     * 获取收藏的歌曲列表 根据用户账号
     * */
    List<HistorySong> getSongs(String account);

    /*
     * 改变歌曲的收藏数量 +1或-1
     * */
    void changeSongStarNumber(Integer songId,Long times);

    /*
     * 是否已经被收藏
     * */
    Boolean stared(String account, Integer songId);
}

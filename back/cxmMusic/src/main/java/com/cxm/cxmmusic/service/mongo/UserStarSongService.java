package com.cxm.cxmmusic.service.mongo;

import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.vo.mongo.HistorySong;
import com.cxm.cxmmusic.vo.mongo.UserStarSong;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zzz06
* @description 针对表【user_star_song】的数据库操作Service
* @createDate 2023-03-18 15:13:34
*/
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
}

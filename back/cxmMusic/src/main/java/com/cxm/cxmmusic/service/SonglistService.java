package com.cxm.cxmmusic.service;

import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.Songlist;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cxm.cxmmusic.vo.SongListWithSongs;

import java.util.List;

/**
* @author zzz06
* @description 针对表【songlist】的数据库操作Service
* @createDate 2023-03-18 15:13:34
*/
public interface SonglistService extends IService<Songlist> {

    /*
     * 新建歌单
     * */
    Boolean newSongList(SongListWithSongs songListWithSongs);

    /*
     * 获取用户的歌单列表
     * */
    List<SongListWithSongs> getByAccount(String account);

    /*
     * 歌单增加歌曲
     * */
    void addSong(Integer songListId, List<Song> songs);

    /*
     * 获取歌单 根据songlistId
     * */
    SongListWithSongs getSongList(Integer songlistId);
}

package com.cxm.cxmmusic.service;

import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.Songlist;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cxm.cxmmusic.vo.SongListWithSongs;
import com.cxm.cxmmusic.vo.mongo.PlaySong;

import java.util.List;


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

    /*
     * 获取歌单的歌曲列表
     * */
    List<PlaySong> getSongs(Integer songlistId);

    /*
     * 删除歌单的部分歌曲
     * */
    Boolean delSongs(Integer songlistId, List<PlaySong> songs);

    /*
    * 删除歌单
    * */

    void delSonglist(Integer songlistId);
}

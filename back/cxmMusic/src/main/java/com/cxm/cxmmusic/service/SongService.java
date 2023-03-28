package com.cxm.cxmmusic.service;

import com.cxm.cxmmusic.pojo.Song;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zzz06
* @description 针对表【song】的数据库操作Service
* @createDate 2023-03-18 15:13:34
*/
public interface SongService extends IService<Song> {

    /*
     * 获取歌曲列表 根据歌手id
     * */
    List<Song> listBySingerId(Integer singerId);
}

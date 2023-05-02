package com.cxm.cxmmusic.service;

import com.cxm.cxmmusic.pojo.Song;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cxm.cxmmusic.vo.Page;
import com.cxm.cxmmusic.vo.SongAllMsg;
import com.cxm.cxmmusic.vo.mongo.HistorySong;

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



    /*
    * 获取歌曲列表（带歌手） 根据搜索条件
    * */
    List<HistorySong> listWithSingerListByCondition(String key);

    /*
     * 获取歌曲列表（带歌手 带标签） 根据搜索条件  分页
     * */
    Page<List<SongAllMsg>> pageWithSingerListByCondition(String key, Integer currentPage, Integer pageSize);
}

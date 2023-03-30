package com.cxm.cxmmusic.service.mongo;

import com.cxm.cxmmusic.vo.Page;
import com.cxm.cxmmusic.vo.mongo.HistorySong;

import java.util.List;

/**
* 用户的歌曲播放记录服务
*/
public interface UserPlaySongService  {

    /*
    * 获取用户的歌曲播放记录
    * */

    Page< List<HistorySong>> getSong(String account, Long currentPage, Integer pageSize);
}

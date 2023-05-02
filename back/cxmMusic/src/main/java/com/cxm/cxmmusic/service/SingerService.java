package com.cxm.cxmmusic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxm.cxmmusic.pojo.Singer;

import java.util.List;

/**
* @author zzz06
* @description 针对表【singer】的数据库操作Service
* @createDate 2023-03-18 15:13:34
*/
public interface SingerService extends IService<Singer> {

    /*
    * 查询歌手名称 列表  根据songId
    * */

    List<String> getSingerNameBySongId(Integer songId);
    /*
     * 查询歌手 列表  根据songId
     * */
    List<Singer> listBySongId(Integer songId);

    /*
    * 更新歌手拥有的歌曲数量
    * */

    void updateSongNumber(Integer singerId);
}

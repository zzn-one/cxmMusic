package com.cxm.cxmmusic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cxm.cxmmusic.pojo.Singer;
import com.baomidou.mybatisplus.extension.service.IService;

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
}

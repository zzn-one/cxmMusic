package com.cxm.cxmmusic.mapper;

import com.cxm.cxmmusic.pojo.Singer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author zzz06
* @description 针对表【singer】的数据库操作Mapper
* @createDate 2023-03-18 15:13:34
* @Entity com.cxm.cxmmusic.pojo.Singer
*/
public interface SingerMapper extends BaseMapper<Singer> {

    /*
     * 查询歌手名称 列表  根据songId
     * */

    List<String> getSingerNameBySongId(Integer songId);


    /*
     * 查询歌手 列表  根据songId
     * */
    List<Singer> listBySongId(Integer songId);

}





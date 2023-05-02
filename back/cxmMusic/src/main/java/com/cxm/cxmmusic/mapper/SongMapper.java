package com.cxm.cxmmusic.mapper;

import com.cxm.cxmmusic.pojo.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxm.cxmmusic.vo.mongo.PlaySong;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author zzz06
* @description 针对表【song】的数据库操作Mapper
* @createDate 2023-03-18 15:13:34
* @Entity com.cxm.cxmmusic.pojo.Song
*/
public interface SongMapper extends BaseMapper<Song> {
    /*
     * 获取歌曲列表 根据歌手id  多表查询
     * */
    List<Song> listBySingerId(Integer singerId);

    /*
     * 获取PlaySong  根据歌曲id  多表查询
     * */
    PlaySong getBySongId(Integer songId);

    /*
     * 获取歌曲列表 根据搜索条件
     * */
    List<Song> listByCondition(String key);


    /*
     * 获取歌曲列表 根据搜索条件 分页
     * */
    List<Song> pageByCondition(@Param("key") String key, @Param("start") Integer start, @Param("size") Integer size);

    /*
     * 获取歌曲列表的总数量 根据搜索条件 分页
     * */
    Long totalByPageCondition(@Param("key") String key, @Param("start") Integer start, @Param("size") Integer size);

}





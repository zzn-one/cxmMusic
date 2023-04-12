package com.cxm.cxmmusic.mapper;

import com.cxm.cxmmusic.pojo.Songlist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxm.cxmmusic.vo.recommend.SumStarNumberAndPlayNumber;

import java.util.List;

/**
* @author zzz06
* @description 针对表【songlist】的数据库操作Mapper
* @createDate 2023-03-18 15:13:34
* @Entity com.cxm.cxmmusic.pojo.Songlist
*/
public interface SonglistMapper extends BaseMapper<Songlist> {
    /*
     * 根据标签分组 统计歌单的播放量和收藏量
     * */
    List<SumStarNumberAndPlayNumber> sumByTag();
}





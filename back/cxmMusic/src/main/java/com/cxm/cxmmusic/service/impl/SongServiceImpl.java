package com.cxm.cxmmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxm.cxmmusic.mapper.DictTagMapper;
import com.cxm.cxmmusic.mapper.SingerMapper;
import com.cxm.cxmmusic.mapper.SongTagMapper;
import com.cxm.cxmmusic.pojo.DictTag;
import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.SongTag;
import com.cxm.cxmmusic.service.DictTagService;
import com.cxm.cxmmusic.service.SongService;
import com.cxm.cxmmusic.mapper.SongMapper;
import com.cxm.cxmmusic.vo.Page;
import com.cxm.cxmmusic.vo.SongAllMsg;
import com.cxm.cxmmusic.vo.mongo.HistorySong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzz06
 * @description 针对表【song】的数据库操作Service实现
 * @createDate 2023-03-18 15:13:34
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song>
        implements SongService {

    @Resource
    private SongMapper songMapper;

    @Resource
    private SingerMapper singerMapper;

    @Resource
    private DictTagMapper tagMapper;

    @Resource
    private SongTagMapper songTagMapper;

    @Override
    public List<Song> listBySingerId(Integer singerId) {

        return songMapper.listBySingerId(singerId);
    }


    @Override
    public List<HistorySong> listWithSingerListByCondition(String key) {

        List<Song> songs = songMapper.listByCondition(key);

        ArrayList<HistorySong> list = new ArrayList<>();

        for (Song song : songs) {
            Integer id = song.getId();

            List<Singer> singers = singerMapper.listBySongId(id);

            HistorySong historySong = new HistorySong(song, null, singers);

            list.add(historySong);
        }

        return list;
    }

    @Override
    public Page<List<SongAllMsg>> pageWithSingerListByCondition(String key, Integer currentPage, Integer pageSize) {

        Integer start = (currentPage - 1) * pageSize;
        Integer size = pageSize;


        List<Song> songs = songMapper.pageByCondition(key, start,size);
        Long total = songMapper.totalByPageCondition(key, start,size);

        ArrayList<SongAllMsg> list = new ArrayList<>();

        for (Song song : songs) {
            Integer id = song.getId();
            //获取歌手列表
            List<Singer> singers = singerMapper.listBySongId(id);

            //获取标签列表
            LambdaQueryWrapper<SongTag> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SongTag::getSongId, id);
            List<SongTag> songTagList = songTagMapper.selectList(queryWrapper);

            ArrayList<DictTag> dictTagArrayList = new ArrayList<>();
            for (SongTag songTag : songTagList) {
                Integer tagId = songTag.getTagId();
                DictTag dictTag = tagMapper.selectById(tagId);
                dictTagArrayList.add(dictTag);
            }
            SongAllMsg songAllMsg = new SongAllMsg(song, singers, dictTagArrayList);

            list.add(songAllMsg);
        }

        Page<List<SongAllMsg>> page = new Page<>();

        page.setRecords(list);
        page.setTotal(total);

        return page;
    }
}





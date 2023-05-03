package com.cxm.cxmmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxm.cxmmusic.mapper.*;
import com.cxm.cxmmusic.pojo.*;
import com.cxm.cxmmusic.service.SongService;

import com.cxm.cxmmusic.vo.SongAllMsg;
import com.cxm.cxmmusic.vo.mongo.HistorySong;
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

    @Resource
    private SingerOwnSongMapper singerOwnSongMapper;

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
    public com.cxm.cxmmusic.vo.Page<List<SongAllMsg>> pageWithSingerListByCondition(String key, Integer currentPage, Integer pageSize) {


//        List<Song> songs = songMapper.pageByCondition(key, start,size);
//        Long total = songMapper.totalByPageCondition(key, start,size);

        LambdaQueryWrapper<Song> songLambdaQueryWrapper = new LambdaQueryWrapper<>();
        songLambdaQueryWrapper.like(key != null, Song::getName, key);
        Page<Song> songPage = new Page<>(currentPage,pageSize);


        Page<Song> selectPage = songMapper.selectPage(songPage, songLambdaQueryWrapper);

        ArrayList<SongAllMsg> list = new ArrayList<>();

        for (Song song : selectPage.getRecords()) {
            Integer id = song.getId();
            //获取歌手列表
            LambdaQueryWrapper<SingerOwnSong> singerOwnSongLambdaQueryWrapper = new LambdaQueryWrapper<>();
            singerOwnSongLambdaQueryWrapper.eq(SingerOwnSong::getSongId, id);
            List<SingerOwnSong> singerOwnSongs = singerOwnSongMapper.selectList(singerOwnSongLambdaQueryWrapper);

            ArrayList<Singer> singers = new ArrayList<>();
            for (SingerOwnSong singerOwnSong : singerOwnSongs) {
                Integer singerId = singerOwnSong.getSingerId();
                Singer singer = singerMapper.selectById(singerId);
                singers.add(singer);
            }

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

        com.cxm.cxmmusic.vo.Page<List<SongAllMsg>> page = new com.cxm.cxmmusic.vo.Page<>();

        page.setRecords(list);
        page.setTotal(selectPage.getTotal());

        return page;
    }
}





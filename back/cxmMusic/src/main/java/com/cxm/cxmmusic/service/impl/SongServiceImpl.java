package com.cxm.cxmmusic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxm.cxmmusic.mapper.SingerMapper;
import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.service.SongService;
import com.cxm.cxmmusic.mapper.SongMapper;
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
    implements SongService{

    @Resource
    private SongMapper songMapper;

    @Resource
    private SingerMapper singerMapper;

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
}





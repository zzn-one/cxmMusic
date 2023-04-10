package com.cxm.cxmmusic.service.mongo.impl;

import com.cxm.cxmmusic.mapper.SingerMapper;
import com.cxm.cxmmusic.mapper.SongMapper;
import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.service.mongo.PlaySongService;
import com.cxm.cxmmusic.vo.mongo.PlaySong;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @create 2023-03-28 17:56
 */
@Service
public class PlaySongServiceImpl implements PlaySongService {

    @Resource
    private SongMapper songMapper;
    @Resource
    private SingerMapper singerMapper;

    @Override
    public PlaySong getBySongId(Integer songId) {
        PlaySong playSong = songMapper.getBySongId(songId);


        List<Singer> singers = singerMapper.listBySongId(songId);
        playSong.setSingerList(singers);
        return playSong;
    }
}

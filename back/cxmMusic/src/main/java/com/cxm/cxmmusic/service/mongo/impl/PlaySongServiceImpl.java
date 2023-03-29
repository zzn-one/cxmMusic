package com.cxm.cxmmusic.service.mongo.impl;

import com.cxm.cxmmusic.mapper.SongMapper;
import com.cxm.cxmmusic.service.mongo.PlaySongService;
import com.cxm.cxmmusic.vo.mongo.PlaySong;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author
 * @create 2023-03-28 17:56
 */
@Service
public class PlaySongServiceImpl implements PlaySongService {

    @Resource
    private SongMapper songMapper;

    @Override
    public PlaySong getBySongId(Integer songId) {
        return songMapper.getBySongId(songId);
    }
}

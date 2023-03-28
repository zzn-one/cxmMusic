package com.cxm.cxmmusic.service.impl;

import com.cxm.cxmmusic.mapper.SongMapper;
import com.cxm.cxmmusic.service.PlaySongService;
import com.cxm.cxmmusic.service.SongService;
import com.cxm.cxmmusic.vo.PlaySong;
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

package com.cxm.cxmmusic.service.mongo.impl;

import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.service.mongo.PlayListService;
import com.cxm.cxmmusic.service.mongo.PlaySongService;
import com.cxm.cxmmusic.vo.mongo.PlayList;
import com.cxm.cxmmusic.vo.mongo.PlaySong;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author
 * @create 2023-03-29 18:06
 */
@Service
public class PlayListServiceImpl implements PlayListService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private PlaySongService playSongService;

    @Override
    public Collection<PlaySong> get(String account) {
        PlayList playList = mongoTemplate.findById(account, PlayList.class);

        Collection<PlaySong> songs = null;
        if (playList != null) {
            songs = playList.getPlaySongMap().values();
        }

        return songs;
    }


    @Override
    public void add(String account, List<Song> songs) {

        //查询该用户是否已经创建了播放列表
        PlayList playListDB = mongoTemplate.findById(account, PlayList.class);

        Map<Integer, PlaySong> map;

        if (playListDB == null) {
            //  创建一个新的 歌曲列表map
            map = new HashMap<>();
        } else {
            // 使用旧的歌曲列表map
            map = playListDB.getPlaySongMap();
        }

        //新增歌曲
        for (Song song : songs) {
            Integer songId = song.getId();
            PlaySong playSong = playSongService.getBySongId(songId);
            //待播放的歌曲添加到 播放列表
            map.putIfAbsent(songId, playSong);
        }
        PlayList playList = new PlayList(account, map);

        mongoTemplate.save(playList);

    }

    @Override
    public Boolean remove(String account, List<Song> songs) {
        boolean result = false;
        //查询该用户是否已经创建了播放列表
        PlayList playListDB = mongoTemplate.findById(account, PlayList.class);

        if (playListDB != null) {
            Map<Integer, PlaySong> songMap = playListDB.getPlaySongMap();


            for (Song song : songs) {
                songMap.remove(song.getId());
            }
            playListDB.setPlaySongMap(songMap);

            mongoTemplate.save(playListDB);
            result = true;
        }

        return result;
    }

    @Override
    public Boolean remove(String account) {
        boolean result = false;
        //获取该用户的播放列表
        PlayList playListDB = mongoTemplate.findById(account, PlayList.class);

        if (playListDB != null) {
            //清空歌曲列表
            playListDB.setPlaySongMap(new HashMap<>());
            mongoTemplate.save(playListDB);

            result = true;
        }
        return result;
    }

    @Override
    public Boolean updatePlayIndex(String account, Integer index) {

        boolean result = false;

        PlayList playList = mongoTemplate.findById(account, PlayList.class);
        if (playList != null) {
            //更新索引
            playList.setCurrentIndex(index);

            mongoTemplate.save(playList);
            result = true;
        }
        return result;
    }

}

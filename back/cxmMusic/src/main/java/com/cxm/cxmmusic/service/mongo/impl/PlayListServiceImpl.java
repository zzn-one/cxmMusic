package com.cxm.cxmmusic.service.mongo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.SonglistOwnSong;
import com.cxm.cxmmusic.service.SonglistOwnSongService;
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

    @Resource
    private SonglistOwnSongService songlistOwnSongService;

    @Override
    public Collection<PlaySong> get(String account) {
        PlayList playList = mongoTemplate.findById(account, PlayList.class);


        Collection<PlaySong> songs = null;
        if (playList != null) {
            songs = playList.getSongList();
        }
        return songs;
    }


    @Override
    public void add(String account, List<Song> songs) {

        //查询该用户是否已经创建了播放列表
        PlayList playListDB = mongoTemplate.findById(account, PlayList.class);

        //存待播放的歌曲
        List<PlaySong> songList;

        if (playListDB == null) {
            //  创建一个新的 歌曲列表
            songList = new ArrayList<>();

        } else {
            // 使用旧的歌曲列表
            songList = playListDB.getSongList();

        }

        //新增歌曲
        for (Song song : songs) {
            Integer songId = song.getId();
            PlaySong playSong = playSongService.getBySongId(songId);
            //待播放的歌曲添加到 播放列表
            songList.removeIf(item -> songId.equals(item.getId()));
            songList.add(0, playSong);
        }

        PlayList playList = new PlayList(account, songList);

        mongoTemplate.save(playList);

    }

    @Override
    public void add(String account, Integer songlistId) {
        //查询该用户是否已经创建了播放列表
        PlayList playListDB = mongoTemplate.findById(account, PlayList.class);

        //存待播放的歌曲
        List<PlaySong> songList;

        if (playListDB == null) {
            //  创建一个新的 歌曲列表
            songList = new ArrayList<>();

        } else {
            // 使用旧的歌曲列表
            songList = playListDB.getSongList();

        }

        //新增歌曲
        //根据歌单id查询出歌曲列表
        LambdaQueryWrapper<SonglistOwnSong> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SonglistOwnSong::getSongListId, songlistId);
        List<SonglistOwnSong> songs = songlistOwnSongService.list(queryWrapper);

        for (SonglistOwnSong song : songs) {
            Integer songId = song.getSongId();
            PlaySong playSong = playSongService.getBySongId(songId);
            //待播放的歌曲添加到 播放列表
            songList.removeIf(item -> songId.equals(item.getId()));
            songList.add(0, playSong);
        }

        PlayList playList = new PlayList(account, songList);

        mongoTemplate.save(playList);
    }

    @Override
    public Boolean remove(String account, List<Song> songs) {
        boolean result = false;
        //查询该用户是否已经创建了播放列表
        PlayList playListDB = mongoTemplate.findById(account, PlayList.class);

        if (playListDB != null) {
            List<PlaySong> songList = playListDB.getSongList();

            for (Song song : songs) {
                songList.removeIf(item -> item.getId().equals(song.getId()));
            }
            playListDB.setSongList(songList);

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
            playListDB.setSongList(new ArrayList<>());
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

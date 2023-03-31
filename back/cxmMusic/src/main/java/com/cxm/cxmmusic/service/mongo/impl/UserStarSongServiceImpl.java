package com.cxm.cxmmusic.service.mongo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.service.SingerService;
import com.cxm.cxmmusic.service.SongService;
import com.cxm.cxmmusic.service.mongo.UserStarSongService;
import com.cxm.cxmmusic.vo.mongo.HistorySong;
import com.cxm.cxmmusic.vo.mongo.UserStarSong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author zzz06
* @description 针对表【user_star_song】的数据库操作Service实现
* @createDate 2023-03-18 15:13:34
*/
@Service
public class UserStarSongServiceImpl implements UserStarSongService{

    @Resource
    private MongoTemplate mongoTemplate;
    @Autowired
    private SongService songService;
    @Autowired
    private SingerService singerService;

    @Override
    public void  starSong(String account, List<Song> songs) {
        //查询是否已经收藏
        for (Song song : songs) {
            Integer songId = song.getId();
            Criteria criteria = Criteria.where("account").is(account).and("songId").is(songId);
            UserStarSong userStarSong = mongoTemplate.findOne(Query.query(criteria), UserStarSong.class);

            if (userStarSong == null) {
                //未收藏 添加收藏记录
                UserStarSong starSong = new UserStarSong(account, songId);
                mongoTemplate.save(starSong);
            }
        }
    }

    @Override
    public void starCancelSong(String account, List<Song> songs) {
        for (Song song : songs) {
            Integer songId = song.getId();
            Criteria criteria = Criteria.where("account").is(account).and("songId").is(songId);
            mongoTemplate.findAndRemove(Query.query(criteria), UserStarSong.class);
        }
    }

    @Override
    public List<HistorySong> getSongs(String account) {

        List<UserStarSong> starSongs = mongoTemplate.find(Query.query(Criteria.where("account").is(account)), UserStarSong.class);

        ArrayList<HistorySong> songs = new ArrayList<>();

        for (UserStarSong starSong : starSongs) {
            Integer songId = starSong.getSongId();
            Song song = songService.getById(songId);
            List<Singer> singerList = singerService.listBySongId(songId);

            HistorySong historySong = new HistorySong(song, null, singerList);
            songs.add(historySong);
        }

        return songs;
    }
}





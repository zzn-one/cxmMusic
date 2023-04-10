package com.cxm.cxmmusic.service.mongo.impl;

import com.cxm.cxmmusic.service.mongo.PlayNumberService;
import com.cxm.cxmmusic.vo.mongo.SongPlayNumber;
import com.cxm.cxmmusic.vo.mongo.SonglistPlayNumber;
import com.cxm.cxmmusic.vo.mongo.UserPlaySong;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author
 * @create 2023-03-29 18:31
 */
@Service
public class PlayNumberServiceImpl implements PlayNumberService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void addSongPlayNumber(Integer songId) {
        /* 歌曲播放量+1 */
        //查询该歌曲是否已在mongoDb有播放量记录
        SongPlayNumber songPlayNumberDb = mongoTemplate.findById(songId, SongPlayNumber.class);
        if (songPlayNumberDb != null) {
            //已有，修改播放量
            songPlayNumberDb.addTimes();

            mongoTemplate.save(songPlayNumberDb);
        } else {
            //未有，新增播放量记录且播放量+1
            SongPlayNumber songPlayNumber = new SongPlayNumber(songId, 1L);

            mongoTemplate.save(songPlayNumber);
        }


    }

    @Override
    public void addUserPlaySongNumber(String account, Integer songId) {
        /* 用户与歌曲id关联 且播放次数+1  记录最新的播放该歌曲的时间 */
        //查询该用户与该歌曲是否已在mongoDb有记录

        Criteria criteria = Criteria.where("account").is(account).and("songId").is(songId);
        UserPlaySong userPlaySongDb = mongoTemplate.findOne(Query.query(criteria), UserPlaySong.class);

        if (userPlaySongDb != null) {
            //mongo有记录
            Long times = userPlaySongDb.getTimes();

            Update update = new Update();
            update.set("times", times + 1);
            update.set("lastedPlayTime", new Date());

            mongoTemplate.updateFirst(Query.query(criteria), update, UserPlaySong.class);
        } else {
            //mongo无记录
            //新增记录
            UserPlaySong userPlaySong = new UserPlaySong(account, songId, 1L, new Date());

            mongoTemplate.save(userPlaySong);
        }

    }

    @Override
    public void addSonglistPlayNumber(Integer songlistId) {
        /* 歌曲播放量+1 */
        //查询该歌单是否已在mongoDb有播放量记录
        SonglistPlayNumber songlistPlayNumber = mongoTemplate.findById(songlistId, SonglistPlayNumber.class);
        if (songlistPlayNumber != null) {
            //已有，修改播放量
            songlistPlayNumber.addTimes();

            mongoTemplate.save(songlistPlayNumber);
        } else {
            //未有，新增播放量记录且播放量+1
            SonglistPlayNumber playNumber = new SonglistPlayNumber(songlistId, 1L);

            mongoTemplate.save(playNumber);
        }
    }
}

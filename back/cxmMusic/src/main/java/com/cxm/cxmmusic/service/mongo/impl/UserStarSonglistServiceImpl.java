package com.cxm.cxmmusic.service.mongo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.Songlist;
import com.cxm.cxmmusic.service.SonglistService;
import com.cxm.cxmmusic.service.mongo.UserStarSonglistService;
import com.cxm.cxmmusic.vo.mongo.SonglistStarNumber;
import com.cxm.cxmmusic.vo.mongo.UserStarSonglist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserStarSonglistServiceImpl implements UserStarSonglistService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Autowired
    private SonglistService songlistService;


    @Override
    public void starSonglist(String account, Integer songlistId) {
        //查询是否已经收藏
        Boolean stared = stared(account, songlistId);
        if (!stared) {
            //新建收藏记录
            UserStarSonglist userStarSonglist = new UserStarSonglist(account, songlistId);

            mongoTemplate.insert(userStarSonglist);

            // 歌单收藏数+1
            changeSonglistStarNumber(songlistId,1L);
        }

    }

    @Override
    public void starCancelSonglist(String account, Integer songlistId) {
        mongoTemplate.findAndRemove(Query.query(Criteria.where("account").is(account).and("songlistId").is(songlistId)), UserStarSonglist.class);

        //  歌单收藏数-1
        changeSonglistStarNumber(songlistId,-1L);

    }

    @Override
    public List<Songlist> getSonglists(String account) {

        ArrayList<Songlist> result = new ArrayList<>();

        //找出该用户的  **收藏**  的所有歌单的id
        List<UserStarSonglist> userStarSonglistList = mongoTemplate.find(Query.query(Criteria.where("account").is(account)), UserStarSonglist.class);

        for (UserStarSonglist starSonglist : userStarSonglistList) {
            Integer songlistId = starSonglist.getSonglistId();
            Songlist songlist = songlistService.getById(songlistId);

            result.add(songlist);
        }
        return result;
    }

    @Override
    public void changeSonglistStarNumber(Integer songlistId, Long times) {
        SonglistStarNumber starNumber;

        starNumber = mongoTemplate.findById(songlistId, SonglistStarNumber.class);

        if (starNumber != null) {
            starNumber.addTimes(times);
        }else {
            //新增记录
            starNumber = new SonglistStarNumber(songlistId, 1L);
        }

        mongoTemplate.save(starNumber);

    }

    @Override
    public Boolean stared(String account, Integer songlistId) {

        UserStarSonglist one = mongoTemplate.findOne(
                Query.query(
                        Criteria.where("account").is(account).and("songlistId").is(songlistId)
                ), UserStarSonglist.class);

        return one != null;
    }
}





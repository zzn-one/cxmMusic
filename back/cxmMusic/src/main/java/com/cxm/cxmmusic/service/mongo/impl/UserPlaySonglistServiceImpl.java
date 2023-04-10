package com.cxm.cxmmusic.service.mongo.impl;

import com.cxm.cxmmusic.service.mongo.UserPlaySonglistService;
import com.cxm.cxmmusic.vo.mongo.UserPlaySonglist;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class UserPlaySonglistServiceImpl implements UserPlaySonglistService {

    @Resource
    private MongoTemplate mongoTemplate;


    @Override
    public void add(String account, Integer songlistId) {
//        查询mongo数据库是否已经有记录
        UserPlaySonglist userPlaySonglist = mongoTemplate.findAndRemove(Query.query(Criteria.where("account").is(account).and("songlistId").is(songlistId)), UserPlaySonglist.class);

        if (userPlaySonglist == null) {
            //无记录  新增
            userPlaySonglist = new UserPlaySonglist(account, songlistId, 1L, new Date());

        } else {
            //有记录  修改播放次数和最近播放时间
            userPlaySonglist.setLastedPlayTime(new Date());
            userPlaySonglist.addTimes();
        }

        //保存到数据库
        mongoTemplate.save(userPlaySonglist);
    }
}





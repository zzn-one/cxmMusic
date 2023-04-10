package com.cxm.cxmmusic.service.mongo.impl;

import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.service.SingerService;
import com.cxm.cxmmusic.service.SongService;
import com.cxm.cxmmusic.service.mongo.UserPlaySongService;
import com.cxm.cxmmusic.vo.Page;
import com.cxm.cxmmusic.vo.mongo.HistorySong;
import com.cxm.cxmmusic.vo.mongo.UserPlaySong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserPlaySongServiceImpl implements UserPlaySongService{

    @Resource
    private MongoTemplate mongoTemplate;
    @Autowired
    private SongService songService;
    @Autowired
    private SingerService singerService;

    @Override
    public Page<List<HistorySong>> getSong(String account, Long currentPage, Integer pageSize) {

        long start = (currentPage - 1) * pageSize;

        ArrayList<HistorySong> historySongArrayList = new ArrayList<>();

        Criteria criteria = Criteria.where("account").is(account);
        Query query = Query.query(criteria);
        query.with(Sort.by(Sort.Order.desc("lastedPlayTime"))).skip(start).limit(pageSize);

        List<UserPlaySong> userPlaySongs = mongoTemplate.find(query, UserPlaySong.class);

        for (UserPlaySong userPlaySong : userPlaySongs) {
            Integer songId = userPlaySong.getSongId();

            Song song = songService.getById(songId);
            List<Singer> singerList = singerService.listBySongId(songId);
            HistorySong historySong = new HistorySong(song, userPlaySong.getLastedPlayTime(),singerList);

            historySongArrayList.add(historySong);
        }

        long total = mongoTemplate.count(Query.query(Criteria.where("account").is(account)), UserPlaySong.class);

        return new Page<>(historySongArrayList, total);
    }
}





package com.cxm.cxmmusic.task;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.service.SongService;
import com.cxm.cxmmusic.vo.mongo.SongPlayNumber;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时把歌曲的播放量写入mysql
 * @create 2023-03-29 15:44
 */

@Component
public class SongPlayNumberTask {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private SongService songService;

    /*
    * 每隔一分钟 就 把歌曲的播放量写入mysql
    * */
    @Scheduled(cron = "0 */1 * * * ?")
    public void writeToMySql(){

        List<SongPlayNumber> playNumberList = mongoTemplate.findAll(SongPlayNumber.class);

        for (SongPlayNumber songPlayNumber : playNumberList) {
            LambdaUpdateWrapper<Song> updateWrapper = new LambdaUpdateWrapper<>();

            updateWrapper.set(Song::getPlayNumber, songPlayNumber.getTimes());
            updateWrapper.eq(Song::getId, songPlayNumber.getSongId());

            songService.update(updateWrapper);
        }

    }
}

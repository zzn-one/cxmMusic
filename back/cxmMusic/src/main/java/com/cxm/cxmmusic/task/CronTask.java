package com.cxm.cxmmusic.task;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.Songlist;
import com.cxm.cxmmusic.pojo.User;
import com.cxm.cxmmusic.service.RecommendService;
import com.cxm.cxmmusic.service.SongService;
import com.cxm.cxmmusic.service.SonglistService;
import com.cxm.cxmmusic.service.UserService;
import com.cxm.cxmmusic.vo.mongo.SongPlayNumber;
import com.cxm.cxmmusic.vo.mongo.SongStarNumber;
import com.cxm.cxmmusic.vo.mongo.SonglistPlayNumber;
import com.cxm.cxmmusic.vo.mongo.SonglistStarNumber;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务
 * @create 2023-03-29 15:44
 */

@Component
public class CronTask {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private SongService songService;

    @Resource
    private SonglistService songlistService;

    @Resource
    private RecommendService recommendService;

    @Resource
    private UserService userService;

    /*
     *每天的00：00：00执行
     * */
    @Scheduled(cron = "0 0 0 * * ?")
    public void everyOneDay(){
        /*
         * 生成新的默认推荐标签列表
         * */
        recommendService.createDefaultTagList();
    }

    /*
    * 每隔一分钟 执行
    * */
    @Scheduled(cron = "0 */1 * * * ?")
    public void everyMinute(){
        //把歌单的播放量写入mysql
        songlistPlayNumberWriteToMySql();

        //把歌曲的播放量写入mysql
        songPlayNumberWriteToMySql();

        //把歌曲的收藏量写入mysql
        songStarNumberWriteToMySql();

        //把歌单的收藏量写入mysql
        songlistStarNumberWriteToMySql();

        //生成新的用户推荐标签列表
        newUserTagList();
    }

    /*
    * 把歌曲的播放量写入mysql
    * */

    public void songPlayNumberWriteToMySql(){

        List<SongPlayNumber> playNumberList = mongoTemplate.findAll(SongPlayNumber.class);

        for (SongPlayNumber songPlayNumber : playNumberList) {
            LambdaUpdateWrapper<Song> updateWrapper = new LambdaUpdateWrapper<>();

            updateWrapper.set(Song::getPlayNumber, songPlayNumber.getTimes());
            updateWrapper.eq(Song::getId, songPlayNumber.getSongId());

            songService.update(updateWrapper);
        }
    }

    /*
     * 把歌曲的收藏量写入mysql
     * */

    public void songStarNumberWriteToMySql(){

        List<SongStarNumber> all = mongoTemplate.findAll(SongStarNumber.class);

        for (SongStarNumber songStarNumber : all) {
            LambdaUpdateWrapper<Song> updateWrapper = new LambdaUpdateWrapper<>();

            updateWrapper.set(Song::getStarNumber, songStarNumber.getTimes());
            updateWrapper.eq(Song::getId, songStarNumber.getSongId());

            songService.update(updateWrapper);
        }
    }

    /*
     * 把歌单的播放量写入mysql
     * */
    public void songlistPlayNumberWriteToMySql(){

        List<SonglistPlayNumber> all = mongoTemplate.findAll(SonglistPlayNumber.class);

        for (SonglistPlayNumber songlistPlayNumber : all) {
            LambdaUpdateWrapper<Songlist> updateWrapper = new LambdaUpdateWrapper<>();

            updateWrapper.eq(Songlist::getId, songlistPlayNumber.getSonglistId());
            updateWrapper.set(Songlist::getPlayNumber, songlistPlayNumber.getTimes());

            songlistService.update(updateWrapper);
        }


    }

    /*
     * 把歌单的收藏量写入mysql
     * */
    public void songlistStarNumberWriteToMySql(){

        List<SonglistStarNumber> all = mongoTemplate.findAll(SonglistStarNumber.class);

        for (SonglistStarNumber songlistStarNumber : all) {
            LambdaUpdateWrapper<Songlist> updateWrapper = new LambdaUpdateWrapper<>();

            updateWrapper.eq(Songlist::getId, songlistStarNumber.getSonglistId());
            updateWrapper.set(Songlist::getStarNumber, songlistStarNumber.getTimes());

            songlistService.update(updateWrapper);
        }


    }

    /*
    * 生成新的用户推荐标签列表
    * */
    public void newUserTagList(){
        List<User> userList = userService.list();

        for (User user : userList) {
            String account = user.getAccount();
            recommendService.createUserTagList(account);
        }

    }



}

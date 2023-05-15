package com.cxm.cxmmusic.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.Songlist;
import com.cxm.cxmmusic.pojo.User;
import com.cxm.cxmmusic.service.*;
import com.cxm.cxmmusic.userBaseRecommend.Recommend;
import com.cxm.cxmmusic.userBaseRecommend.SongTimes;
import com.cxm.cxmmusic.userBaseRecommend.UserSongs;
import com.cxm.cxmmusic.vo.SongListWithSongs;
import com.cxm.cxmmusic.vo.mongo.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务
 *
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

    @Resource
    private SingerService singerService;

    @Resource
    private Recommend recommend;

    @Value("${account}")
    private String officialAccount;

    /*
     *每天的00：00：00执行
     * */
    @Scheduled(cron = "0 0 0 * * ?")
    public void everyOneDay() {

    }

    /*
     * 每隔一分钟 执行
     * */
    @Scheduled(cron = "0 */1 * * * ?")
    public void everyMinute() {
        //把歌单的播放量写入mysql
        songlistPlayNumberWriteToMySql();

        //把歌曲的播放量写入mysql
        songPlayNumberWriteToMySql();

        //把歌曲的收藏量写入mysql
        songStarNumberWriteToMySql();

        //把歌单的收藏量写入mysql
        songlistStarNumberWriteToMySql();

        //更新歌手的歌曲数量
        updateSongNumber();

        //生成新的用户推荐标签列表
        newUserTagList();

        //生成新的默认推荐标签列表
        recommendService.createDefaultTagList();

        //每日为用户推荐歌曲
        userBaseRecommend();
    }

    /*
     * 把歌曲的播放量写入mysql
     * */

    public void songPlayNumberWriteToMySql() {

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

    public void songStarNumberWriteToMySql() {

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
    public void songlistPlayNumberWriteToMySql() {

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
    public void songlistStarNumberWriteToMySql() {

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
    public void newUserTagList() {
        List<User> userList = userService.list();

        for (User user : userList) {
            String account = user.getAccount();
            recommendService.createUserTagList(account);
        }

    }

    /*
     * 更新歌手的歌曲数量
     * */
    public void updateSongNumber() {
        List<Singer> singerList = singerService.list();

        for (Singer singer : singerList) {
            Integer singerId = singer.getId();

            singerService.updateSongNumber(singerId);
        }
    }


    /*
     * 为用户推荐歌曲
     * */
    public void userBaseRecommend() {

        /*
         * 获取官方账号
         * */
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, officialAccount);
        User officialUser = userService.getOne(queryWrapper);

        /*
         * 获取所有用户
         * */
        List<User> userList = userService.list();


        /*
         * 获取所有用户 及其听歌列表
         * */
        List<UserSongs> allUserSongs = getUserSongs();

        for (User user : userList) {
            String account = user.getAccount();
            //获取该用户未听过的歌曲id
            List<Integer> songIdList = recommend.recommend(account, allUserSongs);

            //获取该用户的信息
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getAccount, account);
            User targetUser = userService.getOne(userLambdaQueryWrapper);

            //每30个歌曲id 生成一个歌单
            //设置歌单 元信息
            SongListWithSongs songListWithSongs = new SongListWithSongs();
            songListWithSongs.setAuthorAccount(officialAccount);
            songListWithSongs.setAuthorName(officialUser.getName());
            songListWithSongs.setCreatedTime(new Date());
            songListWithSongs.setName("每日推荐");
            songListWithSongs.setTag("猜你喜欢");
            songListWithSongs.setIntroduction("为用户 " + targetUser.getName() + "(" + account + ")推荐的歌单。");
            ArrayList<Song> songs = new ArrayList<>();
            for (int i = 0; i < songIdList.size(); i++) {

                Integer songId = songIdList.get(i);
                LambdaQueryWrapper<Song> songLambdaQueryWrapper = new LambdaQueryWrapper<>();
                songLambdaQueryWrapper.eq(Song::getId, songId);

                Song song = songService.getOne(songLambdaQueryWrapper);

                songs.add(song);

                if (i % 30 == 0 && i != 0) {
                    //创建歌单
                    songListWithSongs.setSongs(songs);
                    songlistService.newSongList(songListWithSongs);

                    //设置下一个歌单的创建时间
                    songListWithSongs.setCreatedTime(new Date());
                    //清空歌曲列表
                    songs.clear();
                }
            }
        }
    }

    /*
     * 获取所有用户 及其 听歌记录（最近的50首）
     * */
    private List<UserSongs> getUserSongs() {

        ArrayList<UserSongs> userSongsArrayList = new ArrayList<>();

        //遍历用户
        List<User> userList = userService.list();
        for (User user : userList) {
            String account = user.getAccount();

            //找出 该用户的历史听歌记录(50条)
            Criteria criteria = Criteria.where("account").is(account);
            Query query = Query.query(criteria).limit(50).skip(0).with(Sort.by(Sort.Order.desc("lastedPlayTime")));
            List<UserPlaySong> userPlaySongs = mongoTemplate.find(query, UserPlaySong.class);

            //处理听歌记录
            ArrayList<SongTimes> songTimesArrayList = new ArrayList<>();
            for (UserPlaySong userPlaySong : userPlaySongs) {
                Integer songId = userPlaySong.getSongId();
                Long times = userPlaySong.getTimes();
                SongTimes songTimes = new SongTimes(songId, times);
                songTimesArrayList.add(songTimes);
            }

            UserSongs userSongs = new UserSongs(account, songTimesArrayList);

            userSongsArrayList.add(userSongs);
        }

        return userSongsArrayList;
    }

}

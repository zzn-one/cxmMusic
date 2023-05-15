package com.cxm.cxmmusic.myRecommand;

import com.cxm.cxmmusic.pojo.User;
import com.cxm.cxmmusic.service.UserService;
import com.cxm.cxmmusic.testRecommand.Movie;
import com.cxm.cxmmusic.vo.mongo.UserPlaySong;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @create 2023-05-15 2:52
 */
@SpringBootTest
public class testRecommand {


    @Resource
    private UserService userService;

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    void test1() {

        String account = "1000001";

        /*
        * 获取所有用户 及其听歌列表
        * */
        List<UserSongs> allUserSongs = getUserSongs();

        Recommend recommend = new Recommend();
        List<Integer> songIdList = recommend.recommend(account, allUserSongs);

        System.out.println("songIdList = " + songIdList);
    }

    List<UserSongs> getUserSongs() {

        ArrayList<UserSongs> userSongsArrayList = new ArrayList<>();

        //遍历用户
        List<User> userList = userService.list();
        for (User user : userList) {
            String account = user.getAccount();

            //找出 该用户的历史听歌记录
            List<UserPlaySong> userPlaySongs = mongoTemplate.find(
                    Query.query(Criteria.where("account").is(account)), UserPlaySong.class);

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



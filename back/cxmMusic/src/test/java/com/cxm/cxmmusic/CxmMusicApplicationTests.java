package com.cxm.cxmmusic;

import com.cxm.cxmmusic.vo.mongo.PlaySong;
import com.cxm.cxmmusic.vo.mongo.UserPlaySong;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

@SpringBootTest
class CxmMusicApplicationTests {
    @Resource
    BCryptPasswordEncoder passwordEncoder;

    @Resource
    MongoTemplate mongoTemplate;
    @Test
    void contextLoads() {
    }

    @Test
    void passwordEncoder() {
        String password = passwordEncoder.encode("123");
        System.out.println("password = " + password);

        System.out.println(passwordEncoder.matches("123", "$2a$10$1wGI/Cpqz8ALbAB8E.whpe2DfsAlHo3qSqtucwzHW7aanBrGXMQIW"));
    }

    @Test
    void  testMongo(){
        Criteria criteria = Criteria.where("account").is("1000001").and("songId").is(68);
        UserPlaySong userPlaySongDb = mongoTemplate.findOne(Query.query(criteria), UserPlaySong.class);

        System.out.println("userPlaySongDb = " + userPlaySongDb);
    }

    @Test
    void  dskd(){
        HashMap<Integer, PlaySong> map = new HashMap<>();

        map.putIfAbsent(1, new PlaySong());
        map.putIfAbsent(2, new PlaySong());
        map.putIfAbsent(3, new PlaySong());
        map.putIfAbsent(4, new PlaySong());
        map.putIfAbsent(5, new PlaySong());
        map.putIfAbsent(1, new PlaySong());

        Collection<PlaySong> values = map.values();

        System.out.println("values = " + values);


    }

}

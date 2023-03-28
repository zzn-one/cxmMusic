package com.cxm.cxmmusic;

import com.cxm.cxmmusic.vo.PlayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.ArrayList;

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
        PlayList byId = mongoTemplate.findById("1000002", PlayList.class);
        System.out.println("byId = " + byId);
    }

    @Test
    void  dskd(){
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integers.add(i);
        }
        System.out.println("integers1 = " + integers);
        integers.removeIf(integer -> {
            return integer % 5 == 0;
        });
        System.out.println("integers2 = " + integers);


    }

}

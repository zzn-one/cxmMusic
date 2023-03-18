package com.cxm.cxmmusic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class CxmMusicApplicationTests {
    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
    }

    @Test
    void passwordEncoder() {
        String password = passwordEncoder.encode("123");
        System.out.println("password = " + password);

        System.out.println(passwordEncoder.matches("123", "$2a$10$1wGI/Cpqz8ALbAB8E.whpe2DfsAlHo3qSqtucwzHW7aanBrGXMQIW"));
    }

}

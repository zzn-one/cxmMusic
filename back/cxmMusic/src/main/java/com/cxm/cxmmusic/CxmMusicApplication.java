package com.cxm.cxmmusic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.cxm.cxmmusic.mapper")
public class CxmMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(CxmMusicApplication.class, args);
    }

}

package com.cxm.cxmmusic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication

@MapperScan(basePackages = "com.cxm.cxmmusic.mapper")
//swagger接口文档
@EnableOpenApi
//定时任务
@EnableScheduling

public class CxmMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(CxmMusicApplication.class, args);
    }

}

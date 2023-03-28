package com.cxm.cxmmusic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@MapperScan(basePackages = "com.cxm.cxmmusic.mapper")
@EnableOpenApi

public class CxmMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(CxmMusicApplication.class, args);
    }

}

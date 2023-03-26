package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.Exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.service.SingerService;
import com.cxm.cxmmusic.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @create 2023-03-21 12:20
 */
@RestController
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;

    /*
     * 新增歌手
     * */
    @PostMapping()
    public Result<Boolean> addOne(@RequestBody Singer singer) {
        if (singer.getAvatarUrl().equals("")){
            singer.setAvatarUrl(null);
        }
        boolean save = singerService.save(singer);

        return new Result<>(StatusCodeEnum.OK, save);

    }

    /*
     * 获取歌手列表
     * */
    @GetMapping("/list")
    public Result<List<Singer>> list() {

        List<Singer> singers = singerService.list();

        return new Result<>(StatusCodeEnum.OK, singers);

    }

}

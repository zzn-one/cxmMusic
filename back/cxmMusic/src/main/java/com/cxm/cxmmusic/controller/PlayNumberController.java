package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.exception.StatusCodeEnum;
import com.cxm.cxmmusic.service.mongo.PlayNumberService;
import com.cxm.cxmmusic.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @create 2023-03-29 17:57
 */
@Api(tags = "播放量接口")
@RestController
@RequestMapping("/playNumber")
public class PlayNumberController {

    @Autowired
    private PlayNumberService playNumberService;

    @ApiOperation("歌曲播放量+1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "songId", value = "歌曲id"),
            @ApiImplicitParam(name = "account", value = "用户账号")
    })
    @PutMapping("/{account}/{songId}")
    public Result<Boolean> addPlayNumber(@PathVariable("account") String account, @PathVariable("songId") Integer songId) {

        //歌曲播放量+1
        playNumberService.addSongPlayNumber(songId);

        //用户的播放记录+1
        playNumberService.addUserPlaySongNumber(account, songId);


        return new Result<>(StatusCodeEnum.OK, true);
    }

}

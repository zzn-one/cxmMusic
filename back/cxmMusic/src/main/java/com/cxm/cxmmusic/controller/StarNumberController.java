package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.Songlist;
import com.cxm.cxmmusic.service.mongo.UserStarSongService;
import com.cxm.cxmmusic.service.mongo.UserStarSonglistService;
import com.cxm.cxmmusic.vo.Result;
import com.cxm.cxmmusic.vo.mongo.HistorySong;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @create 2023-03-30 15:00
 */
@Api( tags = "收藏功能接口")
@RestController
@RequestMapping("/starNumber")
public class StarNumberController {
    @Autowired
    private UserStarSongService userStarSongService;

    @Autowired
    private UserStarSonglistService userStarSonglistService;


    @ApiOperation("用户收藏歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号"),
            @ApiImplicitParam(name = "songs", value = "歌曲列表")
    })
    @PostMapping("/song/{account}")
    public Result<Boolean> starSong(@PathVariable("account") String account, @RequestBody List<Song> songs) {
        userStarSongService.starSong(account, songs);

        return new Result<>(StatusCodeEnum.OK, true);
    }

    @ApiOperation("用户取消收藏歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号"),
            @ApiImplicitParam(name = "songs", value = "歌曲列表")
    })
    @DeleteMapping("/song/cancel/{account}")
    public Result<Boolean> starCancelSong(@PathVariable("account") String account, @RequestBody List<Song> songs){
        userStarSongService.starCancelSong(account, songs);

        return new Result<>(StatusCodeEnum.OK, true);
    }



    @ApiOperation("获取收藏的歌曲列表 根据用户账号")
    @ApiImplicitParam(name = "account", value = "账号")
    @GetMapping("/song/{account}")
    public Result<List<HistorySong>> getStaredSong(@PathVariable("account") String account) {

        List<HistorySong> songs = userStarSongService.getSongs(account);

        return new Result<>(StatusCodeEnum.OK, songs);
    }



    @ApiOperation("用户收藏歌单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号"),
            @ApiImplicitParam(name = "songlistId", value = "歌单id")
    })
    @PostMapping("/songlist/{account}/{songlistId}")
    public Result<Boolean> starSonglist(@PathVariable("account") String account, @PathVariable("songlistId") Integer songlistId) {

        userStarSonglistService.starSonglist(account, songlistId);

        return new Result<>(StatusCodeEnum.OK, true);
    }

    @ApiOperation("用户取消收藏歌单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号"),
            @ApiImplicitParam(name = "songlistId", value = "歌单id")
    })
    @DeleteMapping("/songlist/cancel/{account}/{songlistId}")
    public Result<Boolean> starCancelSonglist(@PathVariable("account") String account, @PathVariable("songlistId") Integer songlistId){

        userStarSonglistService.starCancelSonglist(account, songlistId);

        return new Result<>(StatusCodeEnum.OK, true);
    }



    @ApiOperation("获取收藏的歌单列表 根据用户账号")
    @ApiImplicitParam(name = "account", value = "账号")
    @GetMapping("/songlist/{account}")
    public Result<List<Songlist>> getStaredSonglist(@PathVariable("account") String account) {

        List<Songlist> songlists = userStarSonglistService.getSonglists(account);

        return new Result<>(StatusCodeEnum.OK, songlists);
    }

    @ApiOperation("歌单是否已经被用户收藏")
    @ApiImplicitParam(name = "account", value = "账号")
    @GetMapping("/songlist/stared/{account}/{songlistId}")
    public Result<Boolean> getStaredSonglist(@PathVariable("account") String account, @PathVariable("songlistId") Integer songlistId) {

        Boolean stared = userStarSonglistService.stared(account, songlistId);
        
        return new Result<>(StatusCodeEnum.OK, stared);
    }

}

package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.service.SonglistService;
import com.cxm.cxmmusic.vo.Result;
import com.cxm.cxmmusic.vo.SongListWithSongs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @create 2023-04-04 13:50
 */
@RestController
@RequestMapping("/songList")
@Api(tags = "歌单接口")
public class SongListController {

    @Autowired
    private SonglistService songlistService;

    @ApiOperation("新建歌单")
    @ApiImplicitParam(value = "歌单vo", name = "songListWithSongs")
    @PostMapping("")
    public Result<Boolean> newSongList(@RequestBody SongListWithSongs songListWithSongs) {
        Boolean aBoolean = songlistService.newSongList(songListWithSongs);
        Result<Boolean> result;

        if (aBoolean) {
            result = new Result<>(StatusCodeEnum.OK, true);
        } else {
            result = new Result<>(StatusCodeEnum.ERROR_NEW_SONGLIST, true);
        }

        return result;
    }

    @ApiOperation("获取用户的歌单列表")
    @ApiImplicitParam(value = "用户账号", name = "account")
    @GetMapping("/list/{account}")
    public Result<List<SongListWithSongs>> getByAccount(@PathVariable("account") String account) {

        List<SongListWithSongs> list = songlistService.getByAccount(account);

        return new Result<>(StatusCodeEnum.OK,list);
    }

    @ApiOperation("歌单增加歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌单id", name = "songlistId"),
            @ApiImplicitParam(value = "歌曲列表", name = "songs")
    })
    @PutMapping("/addSong/{songlistId}")
    public Result<Boolean> addSong(@PathVariable("songlistId") Integer songlistId, @RequestBody List<Song> songs) {
        songlistService.addSong(songlistId, songs);

        return new Result<>(StatusCodeEnum.OK, true);
    }

    @ApiOperation("获取歌单")
    @ApiImplicitParam(value = "歌单id", name = "songlistId")
    @GetMapping("/{songlistId}")
    public Result<SongListWithSongs> getSongList(@PathVariable("songlistId") Integer songlistId) {

        SongListWithSongs songListWithSongs = songlistService.getSongList(songlistId);

        return new Result<>(StatusCodeEnum.OK,songListWithSongs);
    }


}

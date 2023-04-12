package com.cxm.cxmmusic.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxm.cxmmusic.exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.DictTag;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.Songlist;
import com.cxm.cxmmusic.service.DictTagService;
import com.cxm.cxmmusic.service.SonglistService;
import com.cxm.cxmmusic.vo.Result;
import com.cxm.cxmmusic.vo.SongListWithSongs;
import com.cxm.cxmmusic.vo.mongo.PlaySong;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @Autowired
    private DictTagService dictTagService;


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

    @ApiOperation("获取歌单 全部")
    @GetMapping("/list")
    public Result<List<Songlist>> getSongLists() {
//        根据 播放量排序  降序
        LambdaQueryWrapper<Songlist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Songlist::getPlayNumber);

        List<Songlist> songlists = songlistService.list(queryWrapper);


        return new Result<>(StatusCodeEnum.OK,songlists);
    }

    @ApiOperation("获取歌单 多个 根据标签")
    @GetMapping(value = "/list/tag/{tagId}")
    public Result<List<Songlist>> getSongLists(@PathVariable("tagId")Integer tagId) {

        DictTag dictTag = dictTagService.getById(tagId);

        LambdaQueryWrapper<Songlist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Songlist::getTag, dictTag.getText());

        List<Songlist> songlists = songlistService.list(queryWrapper);


        return new Result<>(StatusCodeEnum.OK,songlists);
    }

    @ApiOperation("获取歌单 最多16个 根据标签")
    @GetMapping(value = "/list/tag/limit/{tagId}")
    public Result<Page<Songlist>> getSongListsLimit16(@PathVariable("tagId")Integer tagId) {

        DictTag dictTag = dictTagService.getById(tagId);

        Page<Songlist> page = Page.of(0, 16);
        LambdaQueryWrapper<Songlist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Songlist::getTag, dictTag.getText());

        Page<Songlist> songlistPage = songlistService.page(page, queryWrapper);


        return new Result<>(StatusCodeEnum.OK,songlistPage);
    }


    @ApiOperation("修改歌单信息")
    @ApiImplicitParam(value = "歌单实体", name = "songlist")
    @PutMapping("")
    public Result<Boolean> editSonglistMsg(@RequestBody Songlist songlist){
        Result<Boolean> result;

        boolean update = songlistService.updateById(songlist);
        if (update) {
            result = new Result<>(StatusCodeEnum.OK, true);
        }else {
            result = new Result<>(StatusCodeEnum.ERROR_EDIT_SONGLIST, false);
        }

        return result;
    }

    @ApiOperation("获取歌单的歌曲列表")
    @ApiImplicitParam(value = "歌单id", name = "songlistId")
    @GetMapping("/songs/{songlistId}")
    public Result<List<PlaySong>> getSongs(@PathVariable("songlistId")Integer songlistId) {

        List<PlaySong> songs = songlistService.getSongs(songlistId);

        return new Result<>(StatusCodeEnum.OK, songs);
    }

    @ApiOperation("删除歌单的部分歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌单id", name = "songlistId"),
    })

    @DeleteMapping("/songs/{songlistId}")
    public Result<Boolean> delSongs(@PathVariable("songlistId")Integer songlistId,@RequestBody List<PlaySong> songs) {

        Result<Boolean> result;
        Boolean aBoolean = songlistService.delSongs(songlistId, songs);

        if (aBoolean) {
            result = new Result<>(StatusCodeEnum.OK, true);
        } else {
            result = new Result<>(StatusCodeEnum.ERROR_DELETE_SONGS, true);
        }

        return result;
    }


    @ApiOperation("删除歌单")
    @ApiImplicitParam(value = "歌单id", name = "songlistId")
    @DeleteMapping("/{songlistId}")
    public Result<Boolean> delSonglist(@PathVariable("songlistId")Integer songlistId) {

        songlistService.delSonglist(songlistId);

        return new Result<>(StatusCodeEnum.OK, true);
    }

}

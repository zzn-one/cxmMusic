package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.Exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.service.mongo.PlayListService;
import com.cxm.cxmmusic.vo.mongo.UserPlaySong;
import com.cxm.cxmmusic.service.mongo.PlaySongService;
import com.cxm.cxmmusic.vo.mongo.PlayList;
import com.cxm.cxmmusic.vo.mongo.PlaySong;
import com.cxm.cxmmusic.vo.Result;
import com.cxm.cxmmusic.vo.mongo.SongPlayNumber;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @create 2023-03-28 16:53
 */
@RestController
@RequestMapping("/play")
@Api(tags = "播放器接口")
public class PlayListController {
    @Autowired
    private PlayListService playListService;

    @ApiOperation("获取用户的播放列表")
    @ApiImplicitParam(name = "account", value = "用户账户")
    @GetMapping("/{account}")
    public Result<Collection<PlaySong>> get(@PathVariable("account") String account) {
        Result<Collection<PlaySong>> result;

        Collection<PlaySong> songs = playListService.get(account);

        if (songs == null) {
            result = new Result<>(StatusCodeEnum.ERROR_GET_PLAYLIST, null);
        } else {
            result = new Result<>(StatusCodeEnum.OK, songs);
        }

        return result;
    }

    @ApiOperation("添加歌曲到播放列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户账户"),
            @ApiImplicitParam(name = "songs", value = "歌曲列表"),
    })
    @PutMapping("/{account}")
    public Result<Boolean> add(@PathVariable("account") String account, @RequestBody List<Song> songs) {

        playListService.add(account, songs);
        return new Result<>(StatusCodeEnum.OK, true);

    }

    @ApiOperation("删除部分歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户账户"),
            @ApiImplicitParam(name = "songs", value = "待删除的歌曲列表"),
    })
    @DeleteMapping("/{account}")
    public Result<Boolean> remove(@PathVariable("account") String account, @RequestBody List<Song> songs) {
        Result<Boolean> result;

        Boolean aBoolean = playListService.remove(account, songs);
        if (aBoolean) {
            result = new Result<>(StatusCodeEnum.OK, true);
        } else {
            result = new Result<>(StatusCodeEnum.ERROR_CLEAR_SONG, false);
        }


        return result;
    }

    @ApiOperation("删除所有歌曲")
    @ApiImplicitParam(name = "songs", value = "歌曲列表")
    @DeleteMapping("/all/{account}")
    public Result<Boolean> remove(@PathVariable("account") String account) {
        Result<Boolean> result;

        Boolean aBoolean = playListService.remove(account);

        if (aBoolean) {
            result = new Result<>(StatusCodeEnum.OK, true);
        } else {
            result = new Result<>(StatusCodeEnum.ERROR_CLEAR_SONG, false);
        }

        return result;
    }


    @ApiOperation("更新播放索引")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "index", value = "播放索引"),
            @ApiImplicitParam(name = "account", value = "用户账号")
    })
    @PutMapping("/playIndex/{account}/{index}")
    public Result<Boolean> updatePlayIndex(@PathVariable("account") String account, @PathVariable("index") Integer index) {
        Result<Boolean> result;

        Boolean aBoolean = playListService.updatePlayIndex(account, index);

        if (aBoolean) {
            result = new Result<>(StatusCodeEnum.OK, true);
        } else {
            result = new Result<>(StatusCodeEnum.ERROR_UPDATE_PLAY_INDEX, false);
        }

        return result;
    }

}

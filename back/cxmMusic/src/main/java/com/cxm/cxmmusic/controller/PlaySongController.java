package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.Exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.service.PlaySongService;
import com.cxm.cxmmusic.vo.PlayList;
import com.cxm.cxmmusic.vo.PlaySong;
import com.cxm.cxmmusic.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author
 * @create 2023-03-28 16:53
 */
@RestController
@RequestMapping("/play")
@Api(tags = "播放器接口")
public class PlaySongController {
    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private PlaySongService playSongService;

    @ApiOperation("获取用户的播放列表")
    @ApiImplicitParam(name = "account", value = "用户账户")
    @GetMapping("/{account}")
    public Result<List<PlaySong>> get(@PathVariable("account") String account) {
        PlayList playList = mongoTemplate.findById(account, PlayList.class);

        List<PlaySong> songs = null;
        if (playList != null) {
            songs = playList.getPlaySongList();
        }

        return new Result<>(StatusCodeEnum.OK, songs);
    }

    @ApiOperation("添加歌曲到播放列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户账户"),
            @ApiImplicitParam(name = "songs", value = "歌曲列表"),
    })
    @PutMapping("/{account}")
    public Result<Boolean> add(@PathVariable("account") String account, @RequestBody List<Song> songs) {

        //查询该用户是否已经创建了播放列表
        PlayList playListDB = mongoTemplate.findById(account, PlayList.class);
        if (playListDB == null) {
            //创建一个播放列表并添加歌曲
            ArrayList<PlaySong> playSongs = new ArrayList<>();

            for (Song song : songs) {
                PlaySong playSong = playSongService.getBySongId(song.getId());
                playSongs.add(0, playSong);
            }
            PlayList playList = new PlayList(account, playSongs);

            mongoTemplate.save(playList);
        } else {
            //新增歌曲
            List<PlaySong> playSongList = playListDB.getPlaySongList();
            for (Song song : songs) {
                PlaySong playSong = playSongService.getBySongId(song.getId());
                playSongList.add(0, playSong);
            }
            PlayList playList = new PlayList(account, playSongList);

            mongoTemplate.save(playList);
        }


        return new Result<>(StatusCodeEnum.OK, true);

    }

    @ApiOperation("删除部分歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户账户"),
            @ApiImplicitParam(name = "songs", value = "歌曲列表"),
    })
    @DeleteMapping("/{account}")
    public Result<Boolean> remove(@PathVariable("account") String account, @RequestBody List<Song> songs) {
        //查询该用户是否已经创建了播放列表
        PlayList playListDB = mongoTemplate.findById(account, PlayList.class);

        if (playListDB != null) {
            List<PlaySong> playSongList = playListDB.getPlaySongList();
            for (Song song : songs) {
                playSongList.removeIf(playSong -> playSong.getId().equals(song.getId()));
            }
            playListDB.setPlaySongList(playSongList);

            mongoTemplate.save(playListDB);
        }
        return new Result<>(StatusCodeEnum.OK, true);
    }

    @ApiOperation("删除所有歌曲")
    @ApiImplicitParam(name = "songs", value = "歌曲列表")
    @DeleteMapping("/all/{account}")
    public Result<Boolean> remove(@PathVariable("account") String account) {
        //获取该用户的播放列表
        PlayList playListDB = mongoTemplate.findById(account, PlayList.class);

        if (playListDB != null) {
            //清空歌曲列表
            playListDB.setPlaySongList(new ArrayList<>());
            mongoTemplate.save(playListDB);
        }
        return new Result<>(StatusCodeEnum.OK, true);
    }

}

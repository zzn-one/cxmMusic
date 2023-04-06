package com.cxm.cxmmusic.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cxm.cxmmusic.exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.pojo.SingerOwnSong;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.SongTag;
import com.cxm.cxmmusic.service.SingerOwnSongService;
import com.cxm.cxmmusic.service.SingerService;
import com.cxm.cxmmusic.service.SongService;
import com.cxm.cxmmusic.service.SongTagService;
import com.cxm.cxmmusic.vo.Result;
import com.cxm.cxmmusic.vo.SongAdd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @create 2023-03-21 13:17
 */
@RestController
@RequestMapping("/song")
@Api(tags = "歌曲接口")
public class SongController {

    @Autowired
    private SongService songService;
    @Autowired
    private SingerOwnSongService singerOwnSongService;
    @Autowired
    private SongTagService songTagService;
    @Autowired
    private SingerService singerService;


    @ApiOperation("新增一首歌曲")
    @PostMapping()
    public Result<Boolean> addOne(@RequestBody SongAdd songAdd) {

        Result<Boolean> result = new Result<>(StatusCodeEnum.OK, false);

        String name = songAdd.getName();
        String imgUrl = songAdd.getImgUrl();
        Long duration = songAdd.getDuration();
        String sourceUrl = songAdd.getSourceUrl();
        Integer singerId = songAdd.getSingerId();
        List<Integer> tagIdList = songAdd.getTagIdList();


        Song song = new Song();

        song.setName(name);
        song.setDuration(duration);
        song.setImgUrl(imgUrl);
        song.setSourceUrl(sourceUrl);

        boolean save = songService.save(song);
        if (save) {
            //设置多对多关系
            LambdaQueryWrapper<Song> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Song::getName, name);
            queryWrapper.eq(Song::getSourceUrl, sourceUrl);
            Song songDb = songService.getOne(queryWrapper);

            Integer songDbId = songDb.getId();

            //设置歌手
            SingerOwnSong singerOwnSong = new SingerOwnSong();
            singerOwnSong.setSongId(songDbId);
            singerOwnSong.setSingerId(singerId);

            boolean save1 = singerOwnSongService.save(singerOwnSong);
            if (save1) {
                LambdaUpdateWrapper<Singer> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.setSql("song_number = song_number+1").eq(Singer::getId,singerId);
                singerService.update(updateWrapper);
            }

            //设置标签
            SongTag songTag = new SongTag();
            boolean save2 = true;
            songTag.setSongId(songDbId);
            for (Integer tagId : tagIdList) {
                songTag.setTagId(tagId);
                save2 &= songTagService.save(songTag);
            }

            result = new Result<>(StatusCodeEnum.OK, save1 & save2);
        }

        return result;

    }


    @ApiOperation("获取歌曲列表")
    @ApiImplicitParam(name = "singerId",value = "歌手id")
    @GetMapping("/list/{singerId}")
    public Result<List<Song>> listBySingerId(@PathVariable("singerId")Integer singerId){

        List<Song> songs = songService.listBySingerId(singerId);

        return new Result<>(StatusCodeEnum.OK, songs);

    }
}

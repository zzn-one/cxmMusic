package com.cxm.cxmmusic.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cxm.cxmmusic.Exception.StatusCodeEnum;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author
 * @create 2023-03-21 13:17
 */
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;
    @Autowired
    private SingerOwnSongService singerOwnSongService;
    @Autowired
    private SongTagService songTagService;
    @Autowired
    private SingerService singerService;

    /*
     * 新增歌曲
     * */
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


}

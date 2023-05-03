package com.cxm.cxmmusic.controller;

import com.alibaba.fastjson.JSON;
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
import com.cxm.cxmmusic.vo.Page;
import com.cxm.cxmmusic.vo.Result;
import com.cxm.cxmmusic.vo.SongAdd;
import com.cxm.cxmmusic.vo.SongAllMsg;
import com.cxm.cxmmusic.vo.mongo.HistorySong;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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


    @Value("${file.upload.path}")
    private String FILE_UPLOAD_PATH;

    private final static String SONG_IMG_PREFIX = "song/";


    @ApiOperation("新增一首歌曲")
    @PostMapping()
    public Result<Song> addOne(@RequestBody HashMap<String, Object> map) {

        Song song = JSON.parseObject(JSON.toJSONString(map.get("song")), Song.class);

        List<Integer> tagIdList = JSON.parseArray(JSON.toJSONString(map.get("tagIdList")), Integer.class);

        List<Integer> singerIdList = JSON.parseArray(JSON.toJSONString(map.get("singerIdList")), Integer.class);

        Result<Song> result = new Result<>(StatusCodeEnum.OK, null);


        boolean save = songService.save(song);
        //设置多对多关系
        if (save) {
            //获取刚刚保存的歌曲的id
            LambdaQueryWrapper<Song> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Song::getName, song.getName());
            queryWrapper.eq(Song::getSourceUrl, song.getSourceUrl());
            queryWrapper.eq(Song::getCreateTime, song.getCreateTime());
            Song songDb = songService.getOne(queryWrapper);

            Integer songDbId = songDb.getId();

            //设置歌手
            ArrayList<SingerOwnSong> singerOwnSongs = new ArrayList<>();

            for (Integer singerId : singerIdList) {

                singerService.updateSongNumber(singerId);

                SingerOwnSong singerOwnSong = new SingerOwnSong();
                singerOwnSong.setSongId(songDbId);
                singerOwnSong.setSingerId(singerId);

                singerOwnSongs.add(singerOwnSong);
            }
            boolean save1 = singerOwnSongService.saveBatch(singerOwnSongs);


            //设置标签
            SongTag songTag = new SongTag();
            songTag.setSongId(songDbId);
            ArrayList<SongTag> songTags = new ArrayList<>();

            for (Integer tagId : tagIdList) {
                songTag.setTagId(tagId);
                songTags.add(songTag);
            }

            boolean save2 = songTagService.saveBatch(songTags);

            if (save1 && save2) {
                result = new Result<>(StatusCodeEnum.OK, songDb);
            }

        }

        return result;

    }


    @ApiOperation("获取歌曲列表")
    @ApiImplicitParam(name = "singerId", value = "歌手id")
    @GetMapping("/list/{singerId}")
    public Result<List<Song>> listBySingerId(@PathVariable("singerId") Integer singerId) {

        List<Song> songs = songService.listBySingerId(singerId);

        return new Result<>(StatusCodeEnum.OK, songs);

    }


    @ApiOperation("获取歌曲列表 根据搜索条件")
    @ApiImplicitParam(name = "key", value = "搜索关键字")
    @GetMapping("/list/key/{key}")
    public Result<List<HistorySong>> listBySingerId(@PathVariable("key") String key) {

        List<HistorySong> historySongs = songService.listWithSingerListByCondition(key);

        return new Result<>(StatusCodeEnum.OK, historySongs);

    }

    @ApiOperation("获取歌曲列表 根据搜索条件 分页")
    @ApiImplicitParam(name = "key", value = "搜索关键字")
    @PostMapping("/page/{currentPage}/{pageSize}")
    public Result<Page<List<SongAllMsg>>> listBySingerId(@RequestBody HashMap<String, String> hashMap, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {

        String key = hashMap.get("key");

        Page<List<SongAllMsg>> page = songService.pageWithSingerListByCondition(key, currentPage, pageSize);

        return new Result<>(StatusCodeEnum.OK, page);

    }

    @ApiOperation("删除歌曲")
    @ApiImplicitParam(name = "id", value = "歌曲id")
    @DeleteMapping("/{id}")
    public Result<Boolean> delOne(@PathVariable("id") Integer id) {
        Result<Boolean> result = new Result<>(StatusCodeEnum.OK, false);

        boolean remove = songService.removeById(id);

        if (remove) {
            result = new Result<>(StatusCodeEnum.OK, true);
        }

        return result;

    }

    @ApiOperation("删除歌曲 批量")
    @DeleteMapping("")
    public Result<Boolean> delBatch(@RequestBody List<Song> songList) {
        Result<Boolean> result = new Result<>(StatusCodeEnum.OK, false);

        ArrayList<Integer> songIds = new ArrayList<>();

        for (Song song : songList) {
            Integer id = song.getId();
            songIds.add(id);
        }

        boolean remove = songService.removeBatchByIds(songIds);

        if (remove) {
            result = new Result<>(StatusCodeEnum.OK, true);
        }

        return result;

    }

    @ApiOperation("修改歌曲信息")
    @PutMapping("")
    public Result<Boolean> updateOne(@RequestBody HashMap<String, Object> map) {

        Song song = JSON.parseObject(JSON.toJSONString(map.get("song")), Song.class);
        Integer songId = song.getId();


        List<Integer> tagIdList = JSON.parseArray(JSON.toJSONString(map.get("tagIdList")), Integer.class);

        List<Integer> singerIdList = JSON.parseArray(JSON.toJSONString(map.get("singerIdList")), Integer.class);

        Result<Boolean> result = new Result<>(StatusCodeEnum.OK, false);
        //1.修改歌曲信息
        boolean update = songService.updateById(song);

        //2.修改歌曲的标签

        LambdaQueryWrapper<SongTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SongTag::getSongId, songId);
        //移除原有标签
        songTagService.remove(queryWrapper);
        //添加新标签

        ArrayList<SongTag> songTags = new ArrayList<>();
        for (Integer tagId : tagIdList) {
            SongTag songTag = new SongTag();
            songTag.setSongId(songId);
            songTag.setTagId(tagId);
            songTags.add(songTag);
        }
        boolean saveBatch = songTagService.saveBatch(songTags);

        //3.修改歌曲的歌手
        //移除歌曲原来的歌手
        LambdaQueryWrapper<SingerOwnSong> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SingerOwnSong::getSongId, songId);
        singerOwnSongService.remove(lambdaQueryWrapper);
        //添加新歌手
        ArrayList<SingerOwnSong> singerOwnSongs = new ArrayList<>();
        for (Integer singerId : singerIdList) {
            //更新歌手的歌曲数量
            singerService.updateSongNumber(singerId);

            SingerOwnSong singerOwnSong = new SingerOwnSong();

            singerOwnSong.setSingerId(singerId);
            singerOwnSong.setSongId(songId);

            singerOwnSongs.add(singerOwnSong);
        }

        boolean saveBatch1 = singerOwnSongService.saveBatch(singerOwnSongs);


        if (update && saveBatch && saveBatch1) {
            result = new Result<>(StatusCodeEnum.OK, true);
        }

        return result;
    }

    @ApiOperation("上传歌曲图片")
    @PostMapping("/upload/img")
    public Result<String> uploadImg(MultipartFile file, HttpServletRequest request) {

        if (file == null) {
            return new Result<>(StatusCodeEnum.ERROR_UPLOAD_AVATAR, null);
        }

        int id = Integer.parseInt(request.getParameter("id"));

        String filename = new Date().getTime() + file.getOriginalFilename();
        String imgUrl = FILE_UPLOAD_PATH + SONG_IMG_PREFIX + filename;

//        创建存放歌曲图片的文件夹
        File folder = new File(FILE_UPLOAD_PATH + SONG_IMG_PREFIX);

        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
//        保存文件到本地
        try {
            file.transferTo(new File(folder.getAbsolutePath(), filename));
            //        修改歌曲的图片文件路径
            LambdaUpdateWrapper<Song> updateWrapper = new LambdaUpdateWrapper<>();

            updateWrapper.set(Song::getImgUrl, imgUrl);
            updateWrapper.eq(Song::getId, id);

            songService.update(updateWrapper);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return new Result<>(StatusCodeEnum.OK, imgUrl);
    }

}

package com.cxm.cxmmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxm.cxmmusic.mapper.SongMapper;
import com.cxm.cxmmusic.mapper.SonglistOwnSongMapper;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.Songlist;
import com.cxm.cxmmusic.pojo.SonglistOwnSong;
import com.cxm.cxmmusic.service.SongService;
import com.cxm.cxmmusic.service.SonglistService;
import com.cxm.cxmmusic.mapper.SonglistMapper;
import com.cxm.cxmmusic.service.mongo.PlaySongService;
import com.cxm.cxmmusic.vo.SongListWithSongs;
import com.cxm.cxmmusic.vo.mongo.PlaySong;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zzz06
 * @description 针对表【songlist】的数据库操作Service实现
 * @createDate 2023-03-18 15:13:34
 */
@Service
public class SonglistServiceImpl extends ServiceImpl<SonglistMapper, Songlist>
        implements SonglistService {

    @Resource
    private SonglistMapper songlistMapper;

    @Resource
    private SonglistOwnSongMapper songlistOwnSongMapper;

    @Resource
    private SongMapper songMapper;

    @Resource
    private PlaySongService playSongService;

    @Override
    public Boolean newSongList(SongListWithSongs songListWithSongs) {

        boolean result = false;

        String name = songListWithSongs.getName();
        String authorName = songListWithSongs.getAuthorName();
        String account = songListWithSongs.getAuthorAccount();
        Date createTime = new Date();

        Songlist songlist = new Songlist();
        songlist.setName(name);
        songlist.setAuthorName(authorName);
        songlist.setAuthorAccount(account);
        songlist.setCreatedTime(createTime);

        //创建歌单
        int insert = songlistMapper.insert(songlist);

        if (insert > 0) {
            LambdaQueryWrapper<Songlist> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Songlist::getAuthorAccount, account);
            queryWrapper.eq(Songlist::getCreatedTime, createTime);

            Songlist songListDb = songlistMapper.selectOne(queryWrapper);

            Integer songListDbId = songListDb.getId();

            //歌单添加歌曲
            addSong(songListDbId, songListWithSongs.getSongs());
            result = true;
        }

        return result;
    }


    @Override
    public List<SongListWithSongs> getByAccount(String account) {
        //查询用户创建的歌单
        LambdaQueryWrapper<Songlist> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Songlist::getAuthorAccount, account);

        List<Songlist> songlists = songlistMapper.selectList(queryWrapper);

        ArrayList<SongListWithSongs> result = new ArrayList<>();

        for (Songlist songlist : songlists) {
            SongListWithSongs songListWithSongs = new SongListWithSongs(songlist);

            result.add(songListWithSongs);
        }

        return result;
    }

    @Override
    public void addSong(Integer songListId, List<Song> songs) {

        for (Song song : songs) {
            Integer songId = song.getId();
            SonglistOwnSong songlistOwnSong = new SonglistOwnSong();
            songlistOwnSong.setSongId(songId);
            songlistOwnSong.setSongListId(songListId);

            LambdaQueryWrapper<SonglistOwnSong> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SonglistOwnSong::getSongListId, songListId);
            queryWrapper.eq(SonglistOwnSong::getSongId, songId);

            SonglistOwnSong one = songlistOwnSongMapper.selectOne(queryWrapper);
            if (one == null) {
                songlistOwnSongMapper.insert(songlistOwnSong);

                //修改歌单的歌曲数量
                LambdaUpdateWrapper<Songlist> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.setSql("song_number = song_number + 1");
                updateWrapper.eq(Songlist::getId, songListId);
                songlistMapper.update(null, updateWrapper);
            }


        }
    }

    @Override
    public SongListWithSongs getSongList(Integer songlistId) {
        Songlist songlist = songlistMapper.selectById(songlistId);

        Integer id = songlist.getId();

        LambdaQueryWrapper<SonglistOwnSong> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SonglistOwnSong::getSongListId, id);
        List<SonglistOwnSong> songlistOwnSongs = songlistOwnSongMapper.selectList(queryWrapper);

        ArrayList<Song> songs = new ArrayList<>();
        for (SonglistOwnSong songlistOwnSong : songlistOwnSongs) {
            Integer songId = songlistOwnSong.getSongId();
            Song song = songMapper.selectById(songId);
            songs.add(song);
        }

        SongListWithSongs songListWithSongs = new SongListWithSongs(songlist);
        songListWithSongs.setSongs(songs);


        return songListWithSongs;
    }

    @Override
    public List<PlaySong> getSongs(Integer songlistId) {
        ArrayList<PlaySong> list = new ArrayList<>();

        //根据songlistId 获取歌曲id列表
        LambdaQueryWrapper<SonglistOwnSong> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SonglistOwnSong::getSongListId, songlistId);

        List<SonglistOwnSong> songlistOwnSongs = songlistOwnSongMapper.selectList(queryWrapper);
        //获取歌曲列表
        for (SonglistOwnSong songlistOwnSong : songlistOwnSongs) {
            Integer songId = songlistOwnSong.getSongId();
            PlaySong playSong = playSongService.getBySongId(songId);
            list.add(playSong);
        }

        return list;
    }

    @Override
    public Boolean delSongs(Integer songlistId, List<PlaySong> songs) {

        boolean result = true;

        for (PlaySong song : songs) {
            //删除多对多关系记录
            LambdaQueryWrapper<SonglistOwnSong> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SonglistOwnSong::getSongListId, songlistId);
            Integer songId = song.getId();
            queryWrapper.eq(SonglistOwnSong::getSongId, songId);
            int delete = songlistOwnSongMapper.delete(queryWrapper);
            if (delete == 0) {
                result = false;
            }
            //修改歌单实体的songNumber字段
            LambdaUpdateWrapper<Songlist> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Songlist::getId, songlistId);
            updateWrapper.setSql("song_number=song_number-1");

            songlistMapper.update(null, updateWrapper);

        }

        return result;
    }

}





package com.cxm.cxmmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxm.cxmmusic.mapper.SingerOwnSongMapper;
import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.pojo.SingerOwnSong;
import com.cxm.cxmmusic.service.SingerService;
import com.cxm.cxmmusic.mapper.SingerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author zzz06
* @description 针对表【singer】的数据库操作Service实现
* @createDate 2023-03-18 15:13:34
*/
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer>
    implements SingerService{

    @Resource
    private SingerMapper singerMapper;

    @Resource
    private SingerOwnSongMapper singerOwnSongMapper;


    @Override
    public List<String> getSingerNameBySongId(Integer songId) {
        return singerMapper.getSingerNameBySongId(songId);
    }

    @Override
    public List<Singer> listBySongId(Integer songId) {
        return singerMapper.listBySongId(songId);
    }

    @Override
    public void updateSongNumber(Integer singerId) {

        LambdaQueryWrapper<SingerOwnSong> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SingerOwnSong::getSingerId, singerId);

        Long count = singerOwnSongMapper.selectCount(queryWrapper);

        LambdaUpdateWrapper<Singer> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Singer::getId, singerId);

        updateWrapper.set(Singer::getSongNumber, count);

        singerMapper.update(null, updateWrapper);
    }
}





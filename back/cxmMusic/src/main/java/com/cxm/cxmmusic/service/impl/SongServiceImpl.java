package com.cxm.cxmmusic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.service.SongService;
import com.cxm.cxmmusic.mapper.SongMapper;
import org.springframework.stereotype.Service;

/**
* @author zzz06
* @description 针对表【song】的数据库操作Service实现
* @createDate 2023-03-18 15:13:34
*/
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song>
    implements SongService{

}




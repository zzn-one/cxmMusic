package com.cxm.cxmmusic.service.mongo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxm.cxmmusic.vo.mongo.UserPlaySong;
import com.cxm.cxmmusic.service.mongo.UserPlaySongService;
import com.cxm.cxmmusic.mapper.UserPlaySongMapper;
import org.springframework.stereotype.Service;

/**
* @author zzz06
* @description 针对表【user_play_song】的数据库操作Service实现
* @createDate 2023-03-18 15:13:34
*/
@Service
public class UserPlaySongServiceImpl extends ServiceImpl<UserPlaySongMapper, UserPlaySong>
    implements UserPlaySongService{

}





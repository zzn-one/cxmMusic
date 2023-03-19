package com.cxm.cxmmusic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.service.SingerService;
import com.cxm.cxmmusic.mapper.SingerMapper;
import org.springframework.stereotype.Service;

/**
* @author zzz06
* @description 针对表【singer】的数据库操作Service实现
* @createDate 2023-03-18 15:13:34
*/
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer>
    implements SingerService{

}




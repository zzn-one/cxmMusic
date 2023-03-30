package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.Exception.StatusCodeEnum;
import com.cxm.cxmmusic.service.mongo.UserPlaySongService;
import com.cxm.cxmmusic.vo.Page;
import com.cxm.cxmmusic.vo.Result;
import com.cxm.cxmmusic.vo.mongo.HistorySong;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @create 2023-03-30 13:12
 */
@Api(tags = "用户播放记录接口")
@RestController
@RequestMapping("/userPlay")
public class UserPlayController {
    @Resource
    private UserPlaySongService userPlaySongService;

    @ApiOperation("获取用户播放歌曲的记录 分页查询")
    @ApiImplicitParam(value = "账号", name = "account")
    @GetMapping("/song/{account}/{currentPage}/{pageSize}")
    public Result<Page<List<HistorySong>>> getSong(@PathVariable("account") String account, @PathVariable("currentPage") Long currentPage, @PathVariable("pageSize") Integer pageSize) {


        Page<List<HistorySong>> page = userPlaySongService.getSong(account, currentPage, pageSize);


        return new Result<>(StatusCodeEnum.OK, page);
    }

}

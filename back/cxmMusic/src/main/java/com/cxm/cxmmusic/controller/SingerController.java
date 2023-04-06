package com.cxm.cxmmusic.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxm.cxmmusic.exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.Singer;
import com.cxm.cxmmusic.service.SingerService;
import com.cxm.cxmmusic.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @create 2023-03-21 12:20
 */
@RestController
@RequestMapping("/singer")
@Api(tags = "歌手接口")
public class SingerController {
    @Autowired
    private SingerService singerService;

    /*
     * 新增歌手
     * */
    @ApiOperation("新增一个歌手")
    @PostMapping()
    public Result<Boolean> addOne(@RequestBody Singer singer) {
        if (singer.getAvatarUrl().equals("")) {
            singer.setAvatarUrl(null);
        }
        boolean save = singerService.save(singer);

        return new Result<>(StatusCodeEnum.OK, save);

    }

    /*
     * 获取歌手列表
     * */
    @ApiOperation("获取所有歌手")
    @GetMapping("/list")
    public Result<List<Singer>> list() {

        List<Singer> singers = singerService.list();

        return new Result<>(StatusCodeEnum.OK, singers);

    }

    @ApiOperation("获取歌手列表 分页查询")
    @GetMapping("/list/{currentPage}/{pageSize}")
    public Result<IPage<Singer>> page(@PathVariable("currentPage") Integer currentPage,
                                     @PathVariable("pageSize") Integer pageSize) {

        IPage<Singer> page = new Page<>(currentPage,pageSize);

        LambdaQueryWrapper<Singer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Singer::getSongNumber);

        IPage<Singer> result = singerService.page(page,queryWrapper);

        return new Result<>(StatusCodeEnum.OK, result);
    }


    @ApiOperation("获取一个歌手  根据singerId")
    @ApiImplicitParam(name = "singerId", value = "歌手id")
    @GetMapping("/{singerId}")
    public Result<Singer> getBySingerId(@PathVariable("singerId") Integer id) {

        Result<Singer> result;
        Singer singer = singerService.getById(id);
        if (singer == null) {
            result = new Result<>(StatusCodeEnum.ERROR_GET_SINGER, null);
        } else {
            result = new Result<>(StatusCodeEnum.OK, singer);
        }
        return result;
    }

}

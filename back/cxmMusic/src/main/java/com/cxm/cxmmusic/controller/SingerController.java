package com.cxm.cxmmusic.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

    @Value("${file.upload.path}")
    private String FILE_UPLOAD_PATH;

    private final static String SINGER_AVATAR_PREFIX = "static/img/singer/";

    /*
     * 新增歌手
     * */
    @ApiOperation("新增一个歌手")
    @PostMapping()
    public Result<Singer> addOne(@RequestBody Singer singer) {

        Result<Singer> result = new Result<>(StatusCodeEnum.ERROR_ADD_SINGER, null);

        if (singer.getAvatarUrl().equals("")) {
            singer.setAvatarUrl(null);
        }

        singer.setCreateTime(new Date());

        boolean save = singerService.save(singer);

        if (save) {
            LambdaQueryWrapper<Singer> queryWrapper = new LambdaQueryWrapper<>();

            queryWrapper.eq(Singer::getName, singer.getName());
            queryWrapper.eq(Singer::getCreateTime, singer.getCreateTime());

            Singer one = singerService.getOne(queryWrapper);

            result = new Result<>(StatusCodeEnum.OK, one);
        }


        return result;

    }

    /*
     * 修改歌手信息
     * */
    @ApiOperation("修改歌手信息")
    @PutMapping()
    public Result<Boolean> updateOne(@RequestBody Singer singer) {

        boolean update = singerService.updateById(singer);

        return new Result<>(StatusCodeEnum.OK, update);
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

        IPage<Singer> page = new Page<>(currentPage, pageSize);

        LambdaQueryWrapper<Singer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Singer::getSongNumber);

        IPage<Singer> result = singerService.page(page, queryWrapper);

        return new Result<>(StatusCodeEnum.OK, result);
    }

    @ApiOperation("获取歌手列表 条件分页查询  模糊查询 name、gender、type")
    @GetMapping("/list/{currentPage}/{pageSize}/{key}")
    public Result<IPage<Singer>> pageByCondition(@PathVariable("currentPage") Integer currentPage,
                                                 @PathVariable("pageSize") Integer pageSize, @PathVariable("key") String key) {

        IPage<Singer> page = new Page<>(currentPage, pageSize);

        LambdaQueryWrapper<Singer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Singer::getSongNumber);
        queryWrapper
                .like(key != null, Singer::getName, key)
                .or()
                .like(key != null, Singer::getGender, key)
                .or()
                .like(key != null, Singer::getType, key);

        IPage<Singer> result = singerService.page(page, queryWrapper);

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

    @ApiOperation("获取多个歌手  根据name模糊查询")
    @ApiImplicitParam(name = "name", value = "歌手名称")
    @GetMapping("/list/{name}")
    public Result<List<Singer>> getByName(@PathVariable("name") String name) {

        Result<List<Singer>> result;

        LambdaQueryWrapper<Singer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Singer::getName, name);

        List<Singer> list = singerService.list(queryWrapper);

        if (list == null) {
            result = new Result<>(StatusCodeEnum.ERROR_GET_SINGER, null);
        } else {
            result = new Result<>(StatusCodeEnum.OK, list);
        }
        return result;
    }

    @ApiOperation("上传歌手图片")
    @PostMapping("/upload/avatar")
    public Result<String> uploadAvatar(MultipartFile file, HttpServletRequest request) {

        if (file == null) {
            return new Result<>(StatusCodeEnum.ERROR_UPLOAD_AVATAR, null);
        }

        int singerId = Integer.parseInt(request.getParameter("id"));

        String filename = new Date().getTime() + file.getOriginalFilename();

//        创建存放歌手图片的文件夹
        File folder = new File(FILE_UPLOAD_PATH + SINGER_AVATAR_PREFIX);

        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
//        保存文件到本地
        try {
            file.transferTo(new File(folder.getAbsolutePath(), filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        修改歌手的图片文件路径
        LambdaUpdateWrapper<Singer> updateWrapper = new LambdaUpdateWrapper<>();
        String avatarUrl = SINGER_AVATAR_PREFIX + filename;
        updateWrapper.set(Singer::getAvatarUrl, avatarUrl);
        updateWrapper.eq(Singer::getId, singerId);
        singerService.update(updateWrapper);

        return new Result<>(StatusCodeEnum.OK, avatarUrl);
    }

    @ApiOperation("删除歌手")
    @DeleteMapping("/{id}")
    public Result<Boolean> delOne(@PathVariable("id") Integer id) {
        boolean remove = singerService.removeById(id);

        return new Result<>(StatusCodeEnum.OK, remove);

    }

    @ApiOperation("删除歌手 批量")
    @DeleteMapping()
    public Result<Boolean> delBatch(@RequestBody List<Singer> singerList) {

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (Singer singer : singerList) {
            Integer singerId = singer.getId();
            arrayList.add(singerId);
        }

        boolean batch = singerService.removeBatchByIds(arrayList);

        return new Result<>(StatusCodeEnum.OK, batch);

    }

}

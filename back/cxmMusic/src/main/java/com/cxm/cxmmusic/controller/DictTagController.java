package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.DictTag;
import com.cxm.cxmmusic.service.DictTagService;
import com.cxm.cxmmusic.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author
 * @create 2023-03-21 12:57
 */
@RestController
@RequestMapping("/dict/tag")
@Api(tags = "标签字典接口")
public class DictTagController {

    @Autowired
    private DictTagService dictTagService;

    @GetMapping()
    @ApiOperation("获取标签列表")
    public Result<List<DictTag>> all(){
        List<DictTag> list = dictTagService.list();

        return new Result<>(StatusCodeEnum.OK, list);
    }
}

package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.Exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.DictSingerType;
import com.cxm.cxmmusic.service.DictSingerTypeService;
import com.cxm.cxmmusic.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author
 * @create 2023-03-21 12:11
 */
@RestController
@RequestMapping("/dict/singerType")
public class DictSingerTypeController {
    @Autowired
    private DictSingerTypeService dictSingerTypeService;

    @GetMapping()
    public Result<List<DictSingerType>> all() {
        List<DictSingerType> list = dictSingerTypeService.list();

        return new Result<>(StatusCodeEnum.OK, list);

    }

}

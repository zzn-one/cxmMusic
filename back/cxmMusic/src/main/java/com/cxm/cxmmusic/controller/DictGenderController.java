package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.Exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.DictGender;
import com.cxm.cxmmusic.service.DictGenderService;
import com.cxm.cxmmusic.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @create 2023-03-21 12:08
 */
@RestController
@RequestMapping("/dict/gender")
public class DictGenderController {

    @Autowired
    private DictGenderService dictGenderService;

    @GetMapping()
    public Result<List<DictGender>> all() {
        List<DictGender> list = dictGenderService.list();

        return new Result<>(StatusCodeEnum.OK, list);
    }
}

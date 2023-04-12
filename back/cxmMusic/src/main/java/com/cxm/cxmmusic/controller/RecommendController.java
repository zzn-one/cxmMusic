package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.exception.StatusCodeEnum;
import com.cxm.cxmmusic.service.RecommendService;
import com.cxm.cxmmusic.vo.Result;
import com.cxm.cxmmusic.vo.recommend.RecommendTagList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author
 * @create 2023-04-12 18:37
 */
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Resource
    private RecommendService recommendService;
    /*
    * 获取推荐标签列表
    * */

    @GetMapping("/{account}")
    public Result<RecommendTagList> getTagList(@PathVariable("account")String account){
        RecommendTagList tagList = recommendService.getTagList(account);

        return new Result<>(StatusCodeEnum.OK, tagList);
    }
}

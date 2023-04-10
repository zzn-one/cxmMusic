package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.DictTag;
import com.cxm.cxmmusic.service.DictTagService;
import com.cxm.cxmmusic.vo.Result;
import com.cxm.cxmmusic.vo.TagOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public Result<List<DictTag>> all() {
        List<DictTag> list = dictTagService.list();

        return new Result<>(StatusCodeEnum.OK, list);
    }

    @GetMapping("/order")
    @ApiOperation("获取标签列表 分类")
    public Result<List<TagOrder>> allOrdered() {
        ArrayList<TagOrder> result = new ArrayList<>();

        TagOrder tagOrder = new TagOrder("语种", null);
        TagOrder tagOrder1 = new TagOrder("热门", null);
        TagOrder tagOrder2 = new TagOrder("主题", null);
        TagOrder tagOrder3 = new TagOrder("场景", null);
        TagOrder tagOrder4 = new TagOrder("心情", null);
        TagOrder tagOrder5 = new TagOrder("风格", null);

        List<DictTag> tags = new ArrayList<>();
        List<DictTag> tags1 = new ArrayList<>();
        List<DictTag> tags2 = new ArrayList<>();
        List<DictTag> tags3 = new ArrayList<>();
        List<DictTag> tags4 = new ArrayList<>();
        List<DictTag> tags5 = new ArrayList<>();

        List<DictTag> list = dictTagService.list();
        for (DictTag dictTag : list) {
            Integer tagId = dictTag.getId();
            if (tagId < 200) {
                //语种
                tags.add(dictTag);

            } else if (tagId < 300) {
                //热门
                tags1.add(dictTag);
            } else if (tagId < 400) {
                //主题
                tags2.add(dictTag);
            } else if (tagId < 500) {
                //场景
                tags3.add(dictTag);
            } else if (tagId < 510) {
                //心情
                tags4.add(dictTag);
            } else if (tagId < 600) {
                //风格
                tags5.add(dictTag);
            }
        }

        tagOrder.setTagList(tags);
        tagOrder1.setTagList(tags1);
        tagOrder2.setTagList(tags2);
        tagOrder3.setTagList(tags3);
        tagOrder4.setTagList(tags4);
        tagOrder5.setTagList(tags5);

        result.add(tagOrder);
        result.add(tagOrder1);
        result.add(tagOrder2);
        result.add(tagOrder3);
        result.add(tagOrder4);
        result.add(tagOrder5);

        return new Result<>(StatusCodeEnum.OK, result);
    }


}

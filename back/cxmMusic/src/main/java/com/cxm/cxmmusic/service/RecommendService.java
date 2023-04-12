package com.cxm.cxmmusic.service;

import com.cxm.cxmmusic.vo.recommend.RecommendTagList;

/**
 * 推荐算法 的相关业务功能
 * @create 2023-04-12 8:46
 */
public interface RecommendService {
    /*
     * 获取推荐列表 根据account
     * */
    RecommendTagList getTagList(String account);

    /*
     * 生成默认推荐标签列表
     * */
    void createDefaultTagList();

    /*
     * 生成用户的推荐标签列表
     * */
    void createUserTagList(String account);
}

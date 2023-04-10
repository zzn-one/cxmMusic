package com.cxm.cxmmusic.vo;

import com.cxm.cxmmusic.pojo.DictTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 标签的分类实体
 * @create 2023-04-10 16:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagOrder {
    private String title;
    private List<DictTag> tagList;
}

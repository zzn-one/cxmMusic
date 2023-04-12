package com.cxm.cxmmusic.vo.recommend;

import com.cxm.cxmmusic.pojo.DictTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签的权重
 *
 * @create 2023-04-12 17:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagWeight {

    private DictTag dictTag;

    private Integer weight;

}

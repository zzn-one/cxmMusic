package com.cxm.cxmmusic.vo.recommend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 根据标签分组 统计歌单的播放量和收藏量
 * @create 2023-04-12 8:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SumStarNumberAndPlayNumber {

    private String tag;

    private Long sumStar;

    private Long sumPlay;

    private Long sumTotal;

    /*
    * 计算总和 播放量权重为1 收藏量权重为2
    * */
    public void total(){
        this.sumTotal = this.sumPlay + this.sumStar * 2L;
    }
}

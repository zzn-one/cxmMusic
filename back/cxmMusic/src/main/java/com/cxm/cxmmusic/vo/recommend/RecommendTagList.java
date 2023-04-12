package com.cxm.cxmmusic.vo.recommend;

import com.cxm.cxmmusic.pojo.DictTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * 用于推荐歌单的标签列表
 * @create 2023-04-12 8:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("recommendTagList")
public class RecommendTagList {
    @Id
    private String account;

    @Field
    private List<DictTag> tagList;

    /*
    * 推荐列表的最新更新时间
    * */
    @Field
    private Date updatedTime;

}

package com.cxm.cxmmusic.vo;

import lombok.Data;

import java.util.List;

/**
 * 新增歌曲表单
 * @create 2023-03-21 13:54
 */
@Data
public class SongAdd {
    //name: "",
    //                imgUrl: '',
    //                duration: '',
    //                sourceUrl: "",
    //                tagIdList: [],
    //                singerId: '',
    private String name;
    private String imgUrl;
    private Long duration;
    private String sourceUrl;
    private Integer singerId;
    private List<Integer> tagIdList;
}

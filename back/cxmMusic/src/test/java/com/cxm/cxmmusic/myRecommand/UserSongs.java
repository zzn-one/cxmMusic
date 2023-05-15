package com.cxm.cxmmusic.myRecommand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author
 * @create 2023-05-15 3:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSongs {
    /*
    * 用户账号
    * */
    private String account;

    /*
    * 用户的听歌列表
    * */
    private List<SongTimes> songs;
}

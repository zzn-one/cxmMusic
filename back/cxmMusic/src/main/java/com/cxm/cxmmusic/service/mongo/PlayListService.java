package com.cxm.cxmmusic.service.mongo;

import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.vo.mongo.PlaySong;

import java.util.Collection;
import java.util.List;

/**
 * @author
 * @create 2023-03-29 18:01
 */
public interface PlayListService {
    /*
     * 获取播放列表
     * */
    Collection<PlaySong> get(String account);

    /*
    * 添加歌曲      到播放列表
    * */

    void add(String account, List<Song> songs);
    void add(String account, Integer songlistId);
    /*
    * 删除部分歌曲
    * */
    Boolean remove(String account, List<Song> songs);

    /*
    * 删除所有歌曲
    * */

    Boolean remove(String account);

    /*
    * 更新播放索引
    * */

    Boolean updatePlayIndex(String account, Integer index);

}

package com.cxm.cxmmusic.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.Songlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongListWithSongs {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 歌单名称
     */
    private String name;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 作者的账号  外键
     */
    private String authorAccount;

    /**
     * 标签  外键
     */
    private String tag;

    /**
     * 歌单封面
     */
    private String imgUrl;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 歌单的歌曲数量
     */
    private Integer songNumber;

    /**
     * 播放量
     */
    private Long playNumber;

    /**
     * 收藏量
     */
    private Long starNumber;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 逻辑删除 0删除 1未被删除
     */
    private Integer deleted;

    /**
     *
     */
    @Version
    private Integer version;

    /*
     * 歌单的歌曲列表
     * */
    private List<Song> songs;

    public SongListWithSongs(Songlist songlist) {
        this.songs = null;

        this.id = songlist.getId();
        this.authorAccount = songlist.getAuthorAccount();
        this.authorName = songlist.getAuthorName();
        this.createdTime= songlist.getCreatedTime();
        this.imgUrl = songlist.getImgUrl();

        this.deleted = songlist.getDeleted();
        this.introduction = songlist.getIntroduction();
        this.playNumber = songlist.getPlayNumber();

        this.songNumber = songlist.getSongNumber();
        this.version = songlist.getVersion();
        this.starNumber = songlist.getStarNumber();
        this.tag = songlist.getTag();
        this.name = songlist.getName();

    }
}

package com.cxm.cxmmusic.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName singer_own_song
 */
@TableName(value ="singer_own_song")
@Data
public class SingerOwnSong implements Serializable {
    /**
     * 歌手的id
     */
    @TableField(value = "singerId")
    private Integer singerId;

    /**
     * 歌曲的id
     */
    @TableField(value = "songId")
    private Integer songId;

    /**
     * 
     */
    @TableField(value = "deleted")
    private Integer deleted;

    /**
     * 
     */
    @TableField(value = "version")
    @Version

    private Integer version;

    @TableField(exist = false)

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SingerOwnSong other = (SingerOwnSong) that;
        return (this.getSingerId() == null ? other.getSingerId() == null : this.getSingerId().equals(other.getSingerId()))
            && (this.getSongId() == null ? other.getSongId() == null : this.getSongId().equals(other.getSongId()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSingerId() == null) ? 0 : getSingerId().hashCode());
        result = prime * result + ((getSongId() == null) ? 0 : getSongId().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", singerId=").append(singerId);
        sb.append(", songId=").append(songId);
        sb.append(", deleted=").append(deleted);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
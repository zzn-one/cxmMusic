package com.cxm.cxmmusic.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName dict_account
 */
@TableName(value ="dict_account")
@Data
public class DictAccount implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 
     */
    @TableField(value = "account")
    private Long account;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
package com.cxm.cxmmusic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author
 * @create 2023-03-30 23:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page <T>{
    private T records;

    private Long total;
}

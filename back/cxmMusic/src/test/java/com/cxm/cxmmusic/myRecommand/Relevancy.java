package com.cxm.cxmmusic.myRecommand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author
 * @create 2023-05-15 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relevancy {
    /*
    * （其他）用户账号
    * */
    private String account;
    /*
    * 相关度
    * */
    private Double distance;
}

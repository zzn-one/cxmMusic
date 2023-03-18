package com.cxm.cxmmusic.utils;

/**
 * 生成账号
 *
 * @create 2023-03-18 15:34
 */
public class Account {

    private static Account instance = null;

    private static Long value = 1000000L;

    /* 私有构造方法，防止被实例化 */
    private Account() {
    }

    /* 1:懒汉式，静态工程方法，创建实例 */
    public static Account getInstance() {
        if (instance == null) {
            instance = new Account();
        }
        return instance;
    }

    /*生成账号*/
    public synchronized String getAccount(){
        value += 1;
        return Long.toString(value);
    }
}


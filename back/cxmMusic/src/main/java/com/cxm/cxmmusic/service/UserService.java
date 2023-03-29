package com.cxm.cxmmusic.service;

import com.cxm.cxmmusic.vo.Result;
import com.cxm.cxmmusic.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zzz06
* @description 针对表【user】的数据库操作Service
* @createDate 2023-03-18 15:13:34
*/
public interface UserService extends IService<User> {

    /*
     * 注册
     * @return 账号 字符串
     * */
    String register(String name, String password, String password2);

    /*
     * 登录
     * @return jwt 字符串
     * */
    String login(User user);

    /*
     * 登出
     * */
    Boolean logout(User user);
}

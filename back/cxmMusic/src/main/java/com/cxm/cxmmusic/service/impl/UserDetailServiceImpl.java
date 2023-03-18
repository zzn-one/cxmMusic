package com.cxm.cxmmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cxm.cxmmusic.Exception.GlobalException;
import com.cxm.cxmmusic.Exception.StatusCodeEnum;
import com.cxm.cxmmusic.mapper.UserMapper;
import com.cxm.cxmmusic.pojo.LoginUser;
import com.cxm.cxmmusic.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 *
 * @create 2023-03-18 16:00
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(User::getAccount, account);

        User user = userMapper.selectOne(queryWrapper);

        if (Objects.isNull(user)) {
            //抛出 自定义异常（账号不存在）
            throw new RuntimeException("账号不存在");
        }
        //todo 根据用户查询权限信息

        return new LoginUser(user);
    }
}

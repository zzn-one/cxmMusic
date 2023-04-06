package com.cxm.cxmmusic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxm.cxmmusic.exception.GlobalException;
import com.cxm.cxmmusic.exception.StatusCodeEnum;
import com.cxm.cxmmusic.mapper.UserMapper;
import com.cxm.cxmmusic.vo.LoginUser;
import com.cxm.cxmmusic.pojo.User;
import com.cxm.cxmmusic.service.UserService;
import com.cxm.cxmmusic.utils.AccountUtils;
import com.cxm.cxmmusic.utils.JwtUtils;
import com.cxm.cxmmusic.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zzz06
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-03-18 15:13:34
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public String register(String name, String password, String password2) {
        if (!password.equals(password2)) {
            //两次密码不一致 异常
            throw new GlobalException(StatusCodeEnum.DIFFERENT_PASSWORD);
        }

        //密码加密
        String encode = bCryptPasswordEncoder.encode(password);

        //新增用户
        User newUser = new User();
        //生成账号
        String account = AccountUtils.getInstance().createAccount();
        newUser.setAccount(account);

        newUser.setName(name);
        newUser.setPassword(encode);
        newUser.setRegisterTime(new Date());

        int insert = userMapper.insert(newUser);
        if (insert == 0) {
            //创建失败 异常
            throw new GlobalException(StatusCodeEnum.REGISTER_ERROR);
        }

        return account;
    }

    @Override
    public String login(User user) {
        //账号认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

//        if (Objects.isNull(authenticate)) {
//            //todo 抛出 自定义异常
//            throw new RuntimeException("用户名或密码错误1");
//        }

        //获取登录用户
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String account = loginUser.getUser().getAccount();

        //1.生成jwt
        //设置payload（存在token里的部分用户信息）
        HashMap<String, String> payload = new HashMap<>();
        payload.put("account", account);
        payload.put("name", loginUser.getUser().getName());
        payload.put("avatarUrl", loginUser.getUser().getAvatarUrl());

        String token = JwtUtils.getToken(payload);
        
        //2.更新登录时间
        
        
        loginUser.getUser().setLastedLoginTime(new Date());
        userMapper.updateById(loginUser.getUser());

        //3.登录用户信息存入redis
        redisUtils.setValue(LoginUser.USER_REDIS_PREFIX + account, loginUser, JwtUtils.EXPIRES, TimeUnit.MINUTES);

        return token;
    }

    @Override
    public Boolean logout(User user) {
        //删除redis中的用户信息
        String account = user.getAccount();

        return redisUtils.deleteValue(LoginUser.USER_REDIS_PREFIX + account);
    }


}





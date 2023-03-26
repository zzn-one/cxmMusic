package com.cxm.cxmmusic.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cxm.cxmmusic.Exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.User;
import com.cxm.cxmmusic.service.UserService;
import com.cxm.cxmmusic.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author
 * @create 2023-03-22 13:37
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //    ---------------------------------------------------查---------------------------------------------------------------
    /*
     * 获取用户信息 根据account
     * */
    @GetMapping("/one/{account}")
    public Result<User> getOne(@PathVariable("account") String account) {
        Result<User> result;

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, account);

        User user = userService.getOne(queryWrapper);

        if (user == null) {
            result = new Result<>(StatusCodeEnum.NO_USER, null);
        } else {
            result = new Result<>(StatusCodeEnum.OK, user);
        }
        return result;

    }
//    ---------------------------------------------------改---------------------------------------------------------------

    /*
     * 修改 个人信息
     * */
    @PutMapping("/update/msg")
    public Result<Boolean> updateMsg(@RequestBody User user) {
        Result<Boolean> result;

        boolean update = userService.updateById(user);

        if (update) {
            result = new Result<>(StatusCodeEnum.OK, true);
        } else {
            result = new Result<>(StatusCodeEnum.EXCEPTION, false);
        }

        return result;
    }

    /*
     * 修改 个人密码
     * */
    @PutMapping("/update/password")
    public Result<Boolean> updateMsg(@RequestBody HashMap<String, String> map) {


        Integer id = Integer.parseInt(map.get("id"));
        String account = map.get("account");
        String oldPassword = map.get("oldPassword");
        String oldPassword2 = map.get("oldPassword2");
        String newPassword = map.get("newPassword");
        String newPassword2 = map.get("newPassword2");

        System.out.println("account = " + account);
        System.out.println("oldPassword = " + oldPassword);

        //验证密码是否相同
        if (!oldPassword.equals(oldPassword2)) {
            return new Result<>(StatusCodeEnum.DIFFERENT_OLD_PASSWORD, false);
        }
        if (!newPassword.equals(newPassword2)) {
            return new Result<>(StatusCodeEnum.DIFFERENT_NEW_PASSWORD, false);
        }
        if (newPassword.equals(oldPassword)) {
            return new Result<>(StatusCodeEnum.SAME_NEW_OLD_PASSWORD, false);
        }

        //验证旧密码是否正确
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(User::getAccount, account);

        User userDb = userService.getOne(queryWrapper);

        if (userDb == null) {
            return new Result<>(StatusCodeEnum.NO_USER, false);
        } else {
            //验证旧密码

            String password = userDb.getPassword();
            boolean matches = bCryptPasswordEncoder.matches(oldPassword, password);
            if (matches){
                //密码正确
                LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();

                updateWrapper.eq(User::getId, id);
                updateWrapper.set(User::getPassword, bCryptPasswordEncoder.encode(newPassword));

                boolean update = userService.update(updateWrapper);
                if (!update) {
                    return new Result<>(StatusCodeEnum.ERROR, false);
                } else {
                    return new Result<>(StatusCodeEnum.OK, true);
                }
            }else {
                return new Result<>(StatusCodeEnum.PASSWORD_ERROR, false);
            }
        }


    }

}

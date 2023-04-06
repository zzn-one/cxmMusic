package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.exception.StatusCodeEnum;
import com.cxm.cxmmusic.vo.Result;
import com.cxm.cxmmusic.pojo.User;
import com.cxm.cxmmusic.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author zzn
 * @create 2023-03-18 18:49
 */
@RestController
@RequestMapping("/user")
@Api(tags = "登录注册接口")
public class LoginController {
    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody User user) {
        String token = userService.login(user);
        return new Result<>(StatusCodeEnum.OK, token);

    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public Result<Boolean> logout(@RequestBody User user) {
        Boolean logout = userService.logout(user);
        return new Result<>(StatusCodeEnum.OK, logout);
    }

    @ApiOperation("注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户昵称"),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name = "password2", value = "确认密码")
    })
    @PostMapping("/register")
    public Result<String> register(@RequestBody HashMap<String, String> registerForm) {
        String name = registerForm.get("name");
        String password = registerForm.get("password");
        String password2 = registerForm.get("password2");

        String account = userService.register(name, password, password2);
        return new Result<>(StatusCodeEnum.OK, account);
    }


}

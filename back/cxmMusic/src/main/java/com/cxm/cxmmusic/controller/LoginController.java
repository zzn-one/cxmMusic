package com.cxm.cxmmusic.controller;

import com.cxm.cxmmusic.pojo.Result;
import com.cxm.cxmmusic.pojo.User;
import com.cxm.cxmmusic.service.UserService;
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
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user) {

        return userService.login(user);
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody HashMap<String,String> registerForm) {
        String name = registerForm.get("name");
        String password = registerForm.get("password");
        String password2 = registerForm.get("password2");

        return userService.register(name,password,password2);
    }
}

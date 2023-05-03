package com.cxm.cxmmusic.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxm.cxmmusic.exception.StatusCodeEnum;
import com.cxm.cxmmusic.pojo.Song;
import com.cxm.cxmmusic.pojo.User;
import com.cxm.cxmmusic.service.UserService;
import com.cxm.cxmmusic.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * @author
 * @create 2023-03-22 13:37
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Value("${file.upload.path}")
    private String FILE_UPLOAD_PATH;

    private final static String IMG_PREFIX = "user/";

    //    ---------------------------------------------------查---------------------------------------------------------------

    @ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "account", value = "用户账户", required = true, paramType = "path")
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

    @ApiOperation("获取全部用户信息  分页条件查询")
    @ApiImplicitParam(name = "key", value = "搜索关键字")
    @PostMapping("/list/{currentPage}/{pageSize}")
    public Result<Page<User>> pageCondition(@RequestBody HashMap<String, String> map, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        Result<Page<User>> result = new Result<>(StatusCodeEnum.OK,null);

        String key = map.get("key");
        Page<User> page = new Page<>(currentPage, pageSize);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(key != null, User::getName, key)
                .or()
                .like(key != null, User::getGender, key)
                .or()
                .like(key != null, User::getAccount, key);

        Page<User> userPage = userService.page(page, queryWrapper);

        if (userPage != null) {
            result = new Result<>(StatusCodeEnum.OK,userPage);
        }

        return result;
    }

//    ---------------------------------------------------改---------------------------------------------------------------

    /*
     * 修改 个人信息
     * */
    @ApiOperation("更新用户个人信息")
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
    @ApiOperation("更新用户个人信息")
    @PutMapping("/update/password")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id"),
            @ApiImplicitParam(name = "account"),
            @ApiImplicitParam(name = "oldPassword"),
            @ApiImplicitParam(name = "oldPassword2"),
            @ApiImplicitParam(name = "newPassword"),
            @ApiImplicitParam(name = "newPassword2"),
    })
    public Result<Boolean> updateMsg(@RequestBody HashMap<String, String> map) {


        Integer id = Integer.parseInt(map.get("id"));
        String account = map.get("account");
        String oldPassword = map.get("oldPassword");
        String oldPassword2 = map.get("oldPassword2");
        String newPassword = map.get("newPassword");
        String newPassword2 = map.get("newPassword2");


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
            if (matches) {
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
            } else {
                return new Result<>(StatusCodeEnum.PASSWORD_ERROR, false);
            }
        }


    }


    @ApiOperation("上传用户头像图片")
    @PostMapping("/upload/img")
    public Result<String> uploadImg(MultipartFile file, HttpServletRequest request) {

        if (file == null) {
            return new Result<>(StatusCodeEnum.ERROR_UPLOAD_AVATAR, null);
        }

        int id = Integer.parseInt(request.getParameter("id"));

        String filename = new Date().getTime() + file.getOriginalFilename();
        String imgUrl = FILE_UPLOAD_PATH + IMG_PREFIX + filename;

//        创建存放图片的文件夹
        File folder = new File(FILE_UPLOAD_PATH + IMG_PREFIX);

        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
//        保存文件到本地
        try {
            file.transferTo(new File(folder.getAbsolutePath(), filename));
            //        修改用户的图片文件路径

            LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();

            updateWrapper.set(User::getAvatarUrl, imgUrl);
            updateWrapper.eq(User::getId, id);

            userService.update(updateWrapper);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return new Result<>(StatusCodeEnum.OK, imgUrl);
    }
}

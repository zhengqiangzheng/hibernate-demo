package com.zq.controller;

import com.github.pagehelper.PageInfo;
import com.zq.po.User;
import com.zq.querys.UserQuery;
import com.zq.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zq
 * @create 2018-10-29 23:08
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("user/{userId}")
    public User queryUserById(@PathVariable Integer userId) {
        return userService.queryUserById(userId);
    }

    @PutMapping("user")
    public Integer saveUser(User user) {
        return userService.saveUser(user);
    }

    @GetMapping("user/list")
    public PageInfo<User> queryUsersByParams(UserQuery query) {
        return userService.queryUsersByParams(query);
    }
    @DeleteMapping("user/{userId}")
    public Integer delete( @PathVariable Integer userId) {//也可以使用@PathVariable,地址栏就要用{}
        return userService.delete(userId);
    }
}

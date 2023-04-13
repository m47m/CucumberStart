package com.example.cucumberstart.controller;

import com.example.cucumberstart.common.Result;
import com.example.cucumberstart.entity.User;
import com.example.cucumberstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 13/03/2023 10:27 am
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // 登录
    @GetMapping("/login")
    public Result<?> login(User user) {
        String result = userService.login(user);
        if(result.equals("-1")) {
            return Result.failed("密码错误");
        }else if(result.equals("-2")) {
            return Result.failed("账号不存在");
        }else {
            return Result.success(result);
        }
    }

    // 注册
    @PostMapping("/register")
    public String register(User user) {
        return userService.register(user);
    }

    // 解决查询数据库中文出现乱码问题
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<User> findAll() {
        return userService.findAll();
    }

}

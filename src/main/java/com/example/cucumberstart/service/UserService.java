package com.example.cucumberstart.service;

import com.example.cucumberstart.entity.User;
import com.example.cucumberstart.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 13/03/2023 10:28 am
 */

@Service
public class UserService {
    //autowire:可以对类成员变量、方法以及构造函数进行标注，完成自动装配工作
    @Autowired
    UserMapper userMapper;

    // 登录操作
    public String login(User user) {
        try {
            User userExistN = userMapper.findByName(user.getUsername());
            if (userExistN != null) {
                String userExistP = userMapper.findPswByName(user.getUsername());
                if (userExistP.equals(user.getPassword())) {
                    return user.getUsername()+"登录成功，欢迎您！";
                }else{
                    return "登录失败，密码错误！";
                }
            }else {
                return "登录失败，用户不存在!";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    // 注册操作
    public String register(User user) {
        try {
            User userExist = userMapper.findByName(user.getUsername());
            if (user.getUsername().equals("")) {
                return "用户名不能为空";
            }else if (user.getPassword().equals("")) {
                return "密码不能为空";
            }else if (userExist != null) {
                return "账号已经存在";
            }else {
                userMapper.save(user);
                return "注册成功";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    // 获取所有用户
    public List<User> findAll() {
        List<User> list = userMapper.findAll();

        return list;
    }


}

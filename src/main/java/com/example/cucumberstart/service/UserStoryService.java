package com.example.cucumberstart.service;

import com.example.cucumberstart.entity.User;
import com.example.cucumberstart.entity.UserStory;
import com.example.cucumberstart.mapper.UserStoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 28/03/2023 6:16 pm
 */

@Service
public class UserStoryService {

    @Autowired
    UserStoryMapper userStoryMapper;

    public  UserStory findById(Integer id){
       return userStoryMapper.findById(id);
    }

    public String updateUserStory(UserStory userStory){

        userStoryMapper.updateUserStory(userStory);

        return "更新成功";
    }


    public String createUserStory(UserStory userStory){
        try {
            userStoryMapper.createUserStory(userStory);
            return "创建成功";

        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public List<UserStory> findAll(){
        List<UserStory> list = userStoryMapper.findAll();
        return list;
    }


    public int deleteById(Integer id){
        return userStoryMapper.deleteById(id);
    }
}

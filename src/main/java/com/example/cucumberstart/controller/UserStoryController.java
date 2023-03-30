package com.example.cucumberstart.controller;

import com.example.cucumberstart.common.Result;
import com.example.cucumberstart.entity.User;
import com.example.cucumberstart.entity.UserStory;
import com.example.cucumberstart.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 28/03/2023 6:30 pm
 */

@RestController
@RequestMapping("/userstory")
public class UserStoryController {

    @Autowired
    UserStoryService userStoryService;

//    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public List<UserStory> findAll() {
//        return userStoryService.findAll();
//    }
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<List<UserStory>> findAll() {
          return Result.success(userStoryService.findAll());
    }
    @PostMapping(value = "/create")
    public String createUserStory(@RequestBody UserStory userStory){
        long timestamp = System.currentTimeMillis() / 1000;
        userStory.setCreateTime(timestamp);
        userStoryService.createUserStory(userStory);

        return userStory.toString();
    }

    @PostMapping(value = "/update")
    public Result<UserStory> updateUserStory(@RequestBody UserStory userStory){

        long timestamp = System.currentTimeMillis() / 1000;
        userStory.setModifyTime(timestamp);

        userStoryService.updateUserStory(userStory);

        return Result.success(userStory);
    }

    @GetMapping()
    public Result<UserStory> findById(@RequestParam(value = "userstoryId")Integer id){
//        return userStoryService.findById(id);
        UserStory userStory = userStoryService.findById(id);
        return Objects.isNull(userStory) ? Result.failed() : Result.success(userStory);
    }

    @GetMapping(value = "/delete")
    public Result<?> deleteById(@RequestParam(value = "userstoryId")Integer id){
        int deleteResult = userStoryService.deleteById(id);
        if(deleteResult != 1)
            return Result.failed();
        return Result.success();
    }



}

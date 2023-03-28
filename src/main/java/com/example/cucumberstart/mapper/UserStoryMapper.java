package com.example.cucumberstart.mapper;

import com.example.cucumberstart.entity.User;
import com.example.cucumberstart.entity.UserStory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 28/03/2023 6:16 pm
 */

@Repository
@Mapper
public interface UserStoryMapper {
    List<UserStory> findAll();
    UserStory findById(Integer id);
    void createUserStory(UserStory userStory);

    void updateUserStory(UserStory userStory);
}

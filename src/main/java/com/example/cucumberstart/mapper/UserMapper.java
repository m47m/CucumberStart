package com.example.cucumberstart.mapper;

import com.example.cucumberstart.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 13/03/2023 10:28 am
 */

@Repository
@Mapper
public interface  UserMapper {
    List<User> findAll();
    User findByName(String username);
    String findPswByName(String userName);
    void save(User user);
}

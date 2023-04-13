package com.example.cucumberstart.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 13/03/2023 10:28 am
 */

@Data
@ToString
public class User {
    private Integer id;
    private String username;
    private String password;
    private String role;

}

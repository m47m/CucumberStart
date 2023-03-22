package com.example.cucumberstart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 14/03/2023 6:45 pm
 */

@RestController
@RequestMapping("/test")
public class TestSpringBootController {
    @RequestMapping("/spring")
    public String hello(){
        return "Test Success";
    }
}

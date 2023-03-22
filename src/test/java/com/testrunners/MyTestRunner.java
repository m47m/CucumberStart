package com.testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 14/03/2023 2:20 pm
 */
@CucumberOptions(
        features = {"src/test/resources/static"},
        glue = "src/test/java/com/stepdefinitions",
        monochrome = true
)
public class MyTestRunner extends AbstractTestNGCucumberTests {
}

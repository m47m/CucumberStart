package com.example.cucumberstart.common;

import io.cucumber.core.cli.Main;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 16/03/2023 4:00 pm
 */
public class TestPage {

    private static String[] defaultOptions = {
            "classpath:AppFeatures",

            "-g", "com.stepdefinitions",

            "--plugin", "pretty",
            "--plugin", "json:target/classes/static/cucumber-reports/cucumber-json.json",
            "--plugin", "html:target/classes/static/cucumber-reports/cucumberreport.html"
    };

    public static void main(String[] args) throws Throwable{
        try {

           // Stream<String> cucumberOptions = Stream.concat(Stream.of(defaultOptions), Stream.of(args));
           // Main.run(cucumberOptions.toArray(String[]::new), Thread.currentThread().getContextClassLoader());


            Main.run(defaultOptions,Thread.currentThread().getContextClassLoader());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

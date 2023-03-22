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
            "--glue", "com.stepdefinitions",
           // "--glue", "com.testrunners",

           // "classpath:static",

            "--plugin", "pretty",
            "--plugin", "json:target/classes/static/cucumber-reports/cucumber-json",
            "--plugin", "html:target/classes/static/cucumber-reports/cucumberreport.html"
    };

    public static void main(String[] args) throws Throwable{
        try {

            Stream<String> cucumberOptions = Stream.concat(Stream.of(defaultOptions), Stream.of(args));

            Main.main(cucumberOptions.toArray(String[]::new));

//            Main.main("-g","com.stepdefinitions",
//                    "-g","com.parallel",
//                    "-g","com.testrunners",
//
//                    "classpath:static",
//
//                    "-t","@login",
//
//                    "-p","pretty",
//                    "-p","json:target/cucumber-reports/cucumber-json",
//                    "-p","html:target/cucumber-reports/cucumberreport.html");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
//    @Test
//    public void Test(){
//        try {
//            Main.main("-g","com.stepdefinitions",
//                    "-g","com.parallel",
//                    "-g","com.testrunners",
//
//                    "classpath:static",
//
//                    "-t","@home",
//
//                    "-p","pretty",
//
//                    "-p","json:target/cucumber-reports/cucumber-json",
//                    "-p","html:target/cucumber-reports/cucumberreport.html");
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
}

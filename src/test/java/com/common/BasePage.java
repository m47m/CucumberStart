package com.common;

import io.cucumber.core.cli.Main;
import org.junit.Test;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 16/03/2023 4:00 pm
 */
public class BasePage {
    public static void main(String[] args) throws Throwable{
        try {
            Main.main("-g","com.stepdefinitions",
                    "-g","com.parallel",
                    //"-g","com.testrunners",

                    "classpath:static/Login.feature",
                    //classpath:static",

                    //"-t","@home",

                    "-p","pretty",
                    "-p","json:target/cucumber-reports/cucumber-json",
                    "-p","html:target/cucumber-reports/cucumberreport.html");

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
//                    "-t","@login",
//
//                    "-p","pretty",
//                    "-p","json:target/cucumber-reports/cucumber-json",
//                    "-p","html:target/cucumber-reports/cucumberreport.html");
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
}

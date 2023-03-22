package com.example.cucumberstart.controller;


import com.example.cucumberstart.CucumberStartApplication;
import com.example.cucumberstart.entity.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.example.cucumberstart.common.TestPage;


/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 14/03/2023 10:34 am
 */
@RestController
@RequestMapping("/feature")
public class FileController {
    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping("/list")
    public List<Feature> FeatureList()  throws Exception{
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                .getResources("classpath:static/*.feature");

        List<Feature> features = new ArrayList<>();

        for(Resource resource : resources){

            try(InputStream inputStream = resource.getInputStream()){
                String result = new String(inputStream.readAllBytes());
//                System.out.println(resource.getFilename()+resource.getURL()+resource.contentLength());
                features.add(new Feature(features.size(),resource.getFilename(),resource.lastModified(),resource.contentLength(),result));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return features;
    }


    @RequestMapping("/test")
    public String FeatureTest() {

        String[] argument = {"classpath:static/HomePage.feature"};


        try {
            TestPage.main(argument);
//            return "测试成功！";
        } catch (SecurityException e) {
            System.out.println("Ignore exit");
           return "测试成功~";
        } catch (Throwable throwable) {
            System.out.println("throwable none");
            throwable.printStackTrace();
            return "测试失败";
        }

//        MThread mThread = new MThread("");
//        Thread t1 = new Thread(mThread);
//
//        t1.start();


        return "测试结束";
    }


    @GetMapping("/test")
    public String FeatureTestOne(@RequestParam(value = "featureName")String featureName){


        String[] argument = {"classpath:static/"+featureName};

        System.out.println(argument);


        try {
            TestPage.main(argument);
//            return "测试成功！";
        } catch (SecurityException e) {
            System.out.println("Ignore exit");
            return "测试成功~";
        } catch (Throwable throwable) {
            System.out.println("throwable none");
            throwable.printStackTrace();
            return "测试失败";
        }



        return featureName+ "测试结束";
    }

}

//class MThread implements Runnable{
//
//    private String argument = "";
//    private String result = "测试结束";
//
//    MThread(String argument){
//        this.argument = argument;
//    }
//
//    public String getResult() {
//        return result;
//    }
//
//    public void setResult(String result) {
//        this.result = result;
//    }
//
//    @Override
//    public void run() {
//        try {
//            TestPage.main();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }
//}




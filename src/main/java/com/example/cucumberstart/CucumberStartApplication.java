package com.example.cucumberstart;

import com.example.cucumberstart.utils.IgnoreExitCall;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.support.ResourcePatternUtils;

import com.example.cucumberstart.common.TestPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@SpringBootApplication
public class CucumberStartApplication {


    public static void main(String[] args)  throws Exception {


        ConfigurableApplicationContext applicationContext = SpringApplication.run(CucumberStartApplication.class, args);

        SecurityManager manager = new IgnoreExitCall();
        System.setSecurityManager(manager);

//        try {
//            TestPage.main();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }

//        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(applicationContext)
//                .getResources("classpath:static/*.feature");


//        for(Resource resource:resources){
//                try(InputStream inputStream = resource.getInputStream()){
//                    String result = new String(inputStream.readAllBytes());
//
//                    System.out.println("文件"+resource.getFilename()+"的内容是："+result);
//                } catch (IOException e){
//                    e.printStackTrace();
//                }
//        }
    }

}

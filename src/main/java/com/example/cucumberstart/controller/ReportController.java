package com.example.cucumberstart.controller;

import com.example.cucumberstart.entity.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 16/03/2023 9:03 pm
 */

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping("/test")
    public String getReportOfHtml()  throws Exception{
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                .getResources("classpath:static/**/*.html");

        InputStream inputStream = resources[0].getInputStream();

//        InputStreamReader inputStreamReader
//                = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        String result = new String(inputStream.readAllBytes());


        return result;



    }

}

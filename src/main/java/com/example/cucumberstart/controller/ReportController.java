package com.example.cucumberstart.controller;


import com.example.cucumberstart.common.ReportPage;
import com.example.cucumberstart.common.Result;
import com.example.cucumberstart.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
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
    public Result<?> getReportOfHtml()  throws Exception{
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                .getResources("classpath:static/**/*.json");
        InputStream inputStream = resources[0].getInputStream();

        // read json to String
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String result_ = result.toString(StandardCharsets.UTF_8.name());

        result.close();

       // JSONArray objects = JSON.parseArray(result_);



        return Result.success();

    }

    @PostMapping("/generate")
    public Result getReportOfWord(@RequestBody Report report){
        ReportPage reportPage = new ReportPage();
        try {
            reportPage.createReport(this.resourceLoader,report);
            return Result.success();
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failed();
        }
    }



}

package com.example.cucumberstart.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.cucumberstart.common.ReportPage;
import com.example.cucumberstart.common.Result;
import com.example.cucumberstart.entity.Report;
import com.example.cucumberstart.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ResourceLoader;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;


/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 16/03/2023 9:03 pm
 * @author mhl
 * @version 1.0
 * @since 1.0
 */

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    ReportService reportService;


    @PostMapping("/create")
    public Result<?> createTemplate(@RequestBody Report report){
        long timestamp = System.currentTimeMillis();
        report.setCreateTime(timestamp);
        String createResult =  reportService.createReportTemplate(report);

        if(createResult.equals("0")) {
            return Result.success("success");
        }else{
            return Result.failed("failed");
        }

    }

    @PostMapping("/update")
    public Result<?> updateTemplate(@RequestBody Report report){
        long timestamp = System.currentTimeMillis();
        report.setUpdateTime(timestamp);
        String updateResult =  reportService.updateReportTemplate(report);

        if(updateResult.equals("0")) {
            return Result.success("success");
        }else{
            return Result.failed("failed");
        }

    }

    @GetMapping("/delete")
    public Result<?> deleteTemplate(@RequestParam(value = "id")Integer id){
        String deleteResult =  reportService.deleteReportTemplate(id);

        if(deleteResult.equals("0")) {
            return Result.success("success");
        }else{
            return Result.failed("failed");
        }

    }

    @GetMapping("/all")
    public Result<?> getAllReports(){
        List<Report> reports = reportService.findAllReports();
        return Result.success(reports);
    }

    @RequestMapping("/test")
    public Result<?> getReport()  throws Exception{
//        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
//                .getResources("classpath:static/**/*.json");
//        InputStream inputStream = resources[0].getInputStream();
//
//        // read json to String
//        ByteArrayOutputStream result = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = inputStream.read(buffer)) != -1) {
//            result.write(buffer, 0, length);
//        }
//        String result_ = result.toString(StandardCharsets.UTF_8.name());
//        result.close();


        File file = new File("D:\\notes\\毕业设计\\TestDir\\cucumber-reports\\cucumber.json");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String result = "";
        String s = null;
        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            result += s;
        }
        br.close();

        HashMap<String, Integer> stepResultMap = new HashMap<>();
        HashMap<String, Integer> scenarioResultMap = new HashMap<>();
        HashMap<String, Integer> featureResultMap = new HashMap<>();

        // String to json
        JSONArray jsonArray = JSON.parseArray(result);
        //get elements in jsonArray
        jsonArray.forEach(item->{
            JSONObject jsonObject = (JSONObject) item;
            jsonObject.getJSONArray("elements").forEach(element->{
                JSONObject elementObject = (JSONObject) element;
                elementObject.getJSONArray("steps").forEach(step->{
                    JSONObject stepObject = (JSONObject) step;
//                    System.out.println(stepObject.getString("name")+"-------"+stepObject.getJSONObject("result").getString("status"));
                    stepResultMap.put(stepObject.getJSONObject("result").getString("status"),
                            stepResultMap.getOrDefault(stepObject.getJSONObject("result").getString("status"),0)+1);
                });
            });
        });


        return Result.success();

    }

    //get report of html
    @GetMapping("/html")
    public String getReportOfHtml()  throws Exception{
//        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
//                .getResources("classpath:static/**/*.html");
//
//        InputStream inputStream = resources[0].getInputStream();
//
//        ByteArrayOutputStream result = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = inputStream.read(buffer)) != -1) {
//            result.write(buffer, 0, length);
//        }
//        String result_ = result.toString(StandardCharsets.UTF_8.name());
//        result.close();

        File file = new File("D:\\notes\\毕业设计\\TestDir\\cucumber-reports\\cucumber.html");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String result = "";
        String s = null;
        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            result += s;
        }

        br.close();
        return result;
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

    @GetMapping("/download")
    public void  downloadReportOfWord(HttpServletResponse response){
        String path = "D:\\notes\\毕业设计\\TestDir\\report.docx";
        try{
            FileInputStream inputStream = new FileInputStream(path);

            byte[] buffer = new byte[inputStream.available()];

            inputStream.read(buffer);

            String diskFileName = "report.docx";

            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

            response.setHeader("Content-Disposition", "attachment;filename=\"" + diskFileName+"\"");

            System.out.println("data.length " + buffer.length);

            response.setContentLength(buffer.length);
            response.setHeader("Content-Range", "" + Integer.valueOf(buffer.length - 1));
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Etag", "W/\"9767057-1323779115364\"");
            OutputStream os = response.getOutputStream();

            os.write(buffer);
            //先声明的流后关掉！
            os.flush();
            os.close();
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }


}

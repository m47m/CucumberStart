package com.example.cucumberstart.controller;
import ch.qos.logback.core.util.FileUtil;
import com.cucumbertest.common.BasePage;
import com.example.cucumberstart.CucumberStartApplication;
import com.example.cucumberstart.common.Result;
import com.example.cucumberstart.entity.Feature;
import com.example.cucumberstart.entity.UserStory;
import com.example.cucumberstart.service.UserStoryService;
import org.apache.ibatis.jdbc.Null;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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

    @Autowired
    UserStoryService userStoryService;

    @RequestMapping("/list")
    public List<Feature> FeatureList()  throws Exception{
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                .getResources("classpath:AppFeatures/*.feature");

        List<Feature> features = new ArrayList<>();

        for(Resource resource : resources){

            try(InputStream inputStream = resource.getInputStream()){

                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }

                String result_ = result.toString();

                features.add(new Feature(features.size(),resource.getFilename(),resource.lastModified(),resource.contentLength(),result_));
                result.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return features;
    }

    @GetMapping("/all")
    public List<Feature> FeatureAll() throws Exception{
        File file = new File("D:\\notes\\毕业设计\\TestDir\\AppFeatures");
        File[] tempList = file.listFiles();

        List<Feature> features = new ArrayList<>();

        for (int i = 0; i < tempList.length; i++) {
             if (tempList[i].isFile()) {
                String content = "";
                StringBuilder builder = new StringBuilder();
                InputStreamReader streamReader = new InputStreamReader(new FileInputStream(tempList[i]),"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(streamReader);

                while ((content = bufferedReader.readLine()) != null) {
                    builder.append(content);
                    builder.append("\r\n");
                }
                bufferedReader.close();
                streamReader.close();

                features.add(new Feature(features.size(),tempList[i].getName(),tempList[i].lastModified(),tempList[i].length(),builder.toString()));
             }
        }
        return features;
    }

    @GetMapping("/update")
    public Result<?> FeatureUpdate(){
        File file = new File("D:\\notes\\毕业设计\\TestDir\\AppFeatures");
        try {
            FileUtils.cleanDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        userStoryService.findAll().forEach(userStory -> {
            if(!(userStory.getFeatureName() == null)){
                Path path = Paths.get("D:\\notes\\毕业设计\\TestDir\\AppFeatures\\"+userStory.getFeatureName()+".feature");
                try {
                    Files.createFile(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                try (BufferedWriter writer =
                             Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                    writer.write(userStory.getFeatureContent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

//        //追加写模式
//        try (BufferedWriter writer =
//                     Files.newBufferedWriter(path,
//                             StandardCharsets.UTF_8,
//                             StandardOpenOption.APPEND)){
//            writer.write("!!!Feature End!!!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return Result.success("更新成功");

    }

    @GetMapping("/test")
    public String FeatureTestOne(@RequestParam(value = "featureName")String featureName) throws IOException {
        String[] argument ={};
        Runtime r = Runtime.getRuntime();
        String cucumberJarPath = "D:\\notes\\毕业设计\\TestDir\\cucumber-father-1.0-SNAPSHOT-jar-with-dependencies.jar";

        String featurePath = "";


        if(featureName.equals("")){
           featurePath = "D:\\notes\\毕业设计\\TestDir\\AppFeatures\\";
        }else {
            featurePath = "D:\\notes\\毕业设计\\TestDir\\AppFeatures\\"+featureName+".feature";;
        }
        String cmd = "java -jar "+cucumberJarPath+" "+featurePath;



        try{
            Process proc = r.exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        }catch(Exception e){
            e.printStackTrace();
        }


//        CompletableFuture.runAsync(() -> {
//            try {
//                TestPage.main(argument);
//            } catch (Throwable throwable) {
//                System.out.println("throwable none");
//                throwable.printStackTrace();
//            }
//        });

        return featureName+ "测试结束";
    }

}



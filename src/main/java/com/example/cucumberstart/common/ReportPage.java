package com.example.cucumberstart.common;

import com.example.cucumberstart.entity.Feature;
import com.github.qrpcode.domain.WordGo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 22/03/2023 10:20 am
 */
public class ReportPage {

    private static ResourceLoader resourceLoader;

    public static void main(String[] args) throws IOException {

        //新建word
        WordGo wordGo = new WordGo();

        //项目结构初始化
        wordGo.add("项目测试报告\n","font-size:15;color:#000000;text-align:center");
        wordGo.addLine("","");
        wordGo.add("课题组\n","font-size:15;color:#000000;text-align:center");
        wordGo.addLine("","");
        wordGo.addLine("","");
        wordGo.add("评审日期："+"x"+"年"+"x"+"月"+"x"+"日\n","font-size:15;color:#000000;text-align:center");

        //跳转到下一页
        wordGo.newPage();


        //导言=>编写目的、项目范围、版本更新信息
        wordGo.add("导言\n","font-size:15;color:#000000;font-weight:bold");
        wordGo.add("编写目的\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add("项目范围\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add("版本更新信息\n","font-size:13;color:#000000;font-weight:bold");

        //测试项目介绍=>项目介绍、测试环境、测试方法
        wordGo.add("测试项目介绍\n","font-size:15;color:#000000;font-weight:bold");
        wordGo.add("项目介绍\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add("测试环境\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add("测试方法\n","font-size:13;color:#000000;font-weight:bold");

        //功能测试设计=>*用例+scenario
        wordGo.add("功能测试设计\n","font-size:15;color:#000000;font-weight:bold");

        //获取feature ，并输出
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                .getResources("classpath:static/*.feature");

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

                //String[] name = Objects.requireNonNull(resource.getFilename()).split(".");
                wordGo.add(resource.getFilename()+"模块"+"\n","font-size: 13; color: #000000;font-weight:bold");

                wordGo.add(result_,"font-size: 11; color: #000000");
                result.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //性能测试设计与执行
        wordGo.add("性能测试设计与执行\n","font-size:15;color:#000000;font-weight:bold");

        //测试结论
        wordGo.add("测试结论\n","font-size:15;color:#000000;font-weight:bold");


        //生成文件
        //wordGo.create("E:\\demo.docx");

        ClassPathResource classPathResource = new ClassPathResource("static");

        String resource = classPathResource.getURL().getPath();

        System.out.println(resource);
        wordGo.create(resource+"/report.docx");
    }

    public void createReport(ResourceLoader resourceLoader) throws IOException {
        //新建word
        WordGo wordGo = new WordGo();

        //项目结构初始化
        wordGo.add("项目测试报告\n","font-size:15;color:#000000;text-align:center");
        wordGo.addLine("","");
        wordGo.add("课题组\n","font-size:15;color:#000000;text-align:center");
        wordGo.addLine("","");
        wordGo.addLine("","");
        wordGo.add("评审日期："+"x"+"年"+"x"+"月"+"x"+"日\n","font-size:15;color:#000000;text-align:center");

        //跳转到下一页
        wordGo.newPage();


        //导言=>编写目的、项目范围、版本更新信息
        wordGo.add("一、导言\n","font-size:15;color:#000000;font-weight:bold");
        wordGo.add("1.1编写目的\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add("1.2项目范围\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add("1.3版本更新信息\n","font-size:13;color:#000000;font-weight:bold");

        //测试项目介绍=>项目介绍、测试环境、测试方法
        wordGo.add("二、测试项目介绍\n","font-size:15;color:#000000;font-weight:bold");
        wordGo.add("2.1项目介绍\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add("2.2测试环境\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add("2.3测试方法\n","font-size:13;color:#000000;font-weight:bold");

        //功能测试设计=>*用例+scenario
        wordGo.add("三、功能测试设计\n","font-size:15;color:#000000;font-weight:bold");

        //获取feature ，并输出
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                .getResources("classpath:static/*.feature");

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

                //String[] name = Objects.requireNonNull(resource.getFilename()).split(".");
                wordGo.add("3."+features.size()+" "+resource.getFilename()+"模块"+"\n","font-size: 13; color: #000000;font-weight:bold");

                wordGo.add(result_,"font-size: 11; color: #000000");
                wordGo.addLine("","");
                result.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //性能测试设计与执行
        wordGo.add("性能测试设计与执行\n","font-size:15;color:#000000;font-weight:bold");

        //测试结论
        wordGo.add("测试结论\n","font-size:15;color:#000000;font-weight:bold");


        //生成文件
        //wordGo.create("E:\\demo.docx");

        ClassPathResource classPathResource = new ClassPathResource("static");

        String resource = classPathResource.getURL().getPath();

        System.out.println(resource);
        wordGo.create(resource+"/report.docx");
    }
}

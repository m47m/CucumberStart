package com.example.cucumberstart.common;

import com.example.cucumberstart.entity.Feature;
import com.example.cucumberstart.entity.Report;
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


/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 22/03/2023 10:20 am
 */
public class ReportPage {

    private static ResourceLoader resourceLoader;
    private static String reportPath = "static/report/";
    private static Report report;

    public static void main(String[] args) throws IOException {
        Resource resource = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                .getResource("classpath:static/**/cucumber-json.json");

        InputStream inputStream = resource.getInputStream();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String result_ = result.toString();

        result.close();


    }

    public void createReport(ResourceLoader resourceLoader ,Report report) throws IOException {
        //新建word
        WordGo wordGo = new WordGo();

        //项目结构初始化
        wordGo.addLine("","");
        wordGo.addLine("","");
        wordGo.add(report.getProjectName()+"\n","font-size:15;color:#000000;text-align:center");
        wordGo.add("项目测试报告\n","font-size:15;color:#000000;text-align:center");
        wordGo.addLine("","");
        wordGo.addLine("","");
        wordGo.addLine("","");
        wordGo.add(report.getCurriculumGroup()+"课题组\n","font-size:15;color:#000000;text-align:center");
        wordGo.addLine("","");
        wordGo.addLine("","");
        wordGo.add("评审日期："+"x"+"年"+"x"+"月"+"x"+"日\n","font-size:15;color:#000000;text-align:center");

        //跳转到下一页
        wordGo.newPage();


        //导言=>编写目的、项目范围、版本更新信息
        wordGo.add("一、导言\n","font-size:15;color:#000000;font-weight:bold");

        wordGo.add("1.1编写目的\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add("本文档是针对项目测试和可信评估的报告。文档预期读者包括：\n","font-size:13;color:#000000;");
        wordGo.add("\uF06C\t软件评估人员\n","font-size:13;color:#000000;");
        wordGo.add("\uF06C\t编程人员\n","font-size:13;color:#000000;");
        wordGo.add("\uF06C\t测试人员\n","font-size:13;color:#000000;");
        wordGo.add("\uF06C\t用户\n","font-size:13;color:#000000;");

        wordGo.add("1.2项目范围\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add("本文档主要包括项目的功能和性能测试结果分析，以及相应的可信研究报告。\n","font-size:13;color:#000000;");

        wordGo.add("1.3版本更新信息\n","font-size:13;color:#000000;font-weight:bold");

        //测试项目介绍=>项目介绍、测试环境、测试方法
        wordGo.add("二、测试项目介绍\n","font-size:15;color:#000000;font-weight:bold");

        wordGo.add("2.1项目介绍\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add(report.getProjectIntroduction()+"\n","font-size:13;color:#000000;");

        wordGo.add("2.2测试环境\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add("(1)\t网络环境：\n","font-size:13;color:#000000;");
        wordGo.add(report.getNetwork(),"font-size:13;color:#000000;");
        wordGo.add("(2)\t硬件环境：\n","font-size:13;color:#000000;");
        wordGo.add(report.getHardware(),"font-size:13;color:#000000;");
        wordGo.add("(3)\t软件环境：\n","font-size:13;color:#000000;");
        wordGo.add(report.getSoftware(),"font-size:13;color:#000000;");

        wordGo.add("2.3测试方法\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add(report.getTestMethod()+"\n","font-size:13;color:#000000;");

        //功能测试设计=>*用例+scenario
        wordGo.add("三、功能测试设计\n","font-size:15;color:#000000;font-weight:bold");
        wordGo.add("测试中采用了"+report.getScriptGenerationTool()+"工具生成测试脚本，得到可运行的脚本文件，并应用测试脚本进行后续自动化测试和回归测试。下面介绍测试用例设计",
                "font-size:13;color:#000000;");

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
        wordGo.add(report.getTestConclusion(),"font-size:13;color:#000000;");

        //生成文件
        //wordGo.create("E:\\demo.docx");

        ClassPathResource classPathResource = new ClassPathResource("static");

        String resource = classPathResource.getURL().getPath();

        System.out.println(resource);
        wordGo.create(resource+"/report.docx");
    }
}

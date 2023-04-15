package com.example.cucumberstart.common;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.cucumberstart.entity.Feature;
import com.example.cucumberstart.entity.Report;
import com.github.qrpcode.domain.WordGo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
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

                wordGo.add("3."+features.size()+" "+tempList[i].getName()+"模块"+"\n","font-size: 13; color: #000000;font-weight:bold");
                wordGo.add(builder.toString(),"font-size: 11; color: #000000");
                wordGo.addLine("","");
            }
        }



        //性能测试设计与执行
        wordGo.add("四、性能测试设计与执行\n","font-size:15;color:#000000;font-weight:bold");

        //测试结论
        wordGo.add("五、测试结论\n","font-size:15;color:#000000;font-weight:bold");
        wordGo.add("5.1测试用例执行结论\n","font-size:13;color:#000000;font-weight:bold");

        File fileofJson = new File("D:\\notes\\毕业设计\\TestDir\\cucumber-reports\\cucumber.json");
        BufferedReader br = new BufferedReader(new FileReader(fileofJson));
        String resultTestJson = "";
        String s = null;
        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            resultTestJson += s;
        }
        br.close();

        HashMap<String, Integer> stepResultMap = new HashMap<>();
        HashMap<String, String> scenarioResultInfoMap = new HashMap<>();
        HashMap<String, Integer> scenarioResultMap = new HashMap<>();
        HashMap<String, String> featureResultInfoMap = new HashMap<>();
        HashMap<String, Integer> featureResultMap = new HashMap<>();

        int featureSize = 0;
        int scenarioSize = 0;
        int stepsSize = 0;
        // String to json
        JSONArray jsonArrayFeatures = JSON.parseArray(resultTestJson);
        featureSize = jsonArrayFeatures.size();

        for(int i = 0;i<jsonArrayFeatures.size();i++){
            //one feature result
            JSONObject jsonObjectFeature = jsonArrayFeatures.getJSONObject(i);

            featureResultInfoMap.put(jsonObjectFeature.getString("name"),checkFeature(jsonObjectFeature));
            featureResultMap.put(checkFeature(jsonObjectFeature),featureResultMap.getOrDefault(checkFeature(jsonObjectFeature),0)+1);
            JSONArray jsonArrayElements = jsonObjectFeature.getJSONArray("elements");
            scenarioSize += jsonArrayElements.size();

            for(int j = 0;j<jsonArrayElements.size();j++){
                //one scenario result
                JSONObject jsonObjectElement = jsonArrayElements.getJSONObject(j);
                JSONArray jsonArraySteps = jsonObjectElement.getJSONArray("steps");
                stepsSize += jsonArraySteps.size();

                scenarioResultInfoMap.put(jsonObjectElement.getString("name"),checkScenario(jsonObjectElement));
                scenarioResultMap.put(checkScenario(jsonObjectElement),scenarioResultMap.getOrDefault(checkScenario(jsonObjectElement),0)+1);

                for(int k = 0;k<jsonArraySteps.size();k++){
                    //one step result
                    JSONObject jsonObjectSteps = jsonArraySteps.getJSONObject(k);
                    String status = jsonObjectSteps.getJSONObject("result").getString("status");
                    stepResultMap.put(status,stepResultMap.getOrDefault(status,0)+1);
                }
            }
        }



        stepsSize = stepResultMap.values().stream().mapToInt(value -> value).sum();
        wordGo.add("本次测试测试用例共"+featureSize+"个。具体测试结果如下\n","font-size:13;color:#000000;");
        featureResultMap.forEach((key,value)->{
            wordGo.add(key+"："+value+"个\n","font-size:13;color:#000000;");
        });
        wordGo.add("本次测试测试场景共"+scenarioSize+"个。具体测试结果如下\n","font-size:13;color:#000000;");
        scenarioResultMap.forEach((key,value)->{
            wordGo.add(key+"："+value+"个\n","font-size:13;color:#000000;");
        });
        wordGo.add("本次测试测试步骤共"+stepsSize+"步。具体测试结果如下\n","font-size:13;color:#000000;");
        stepResultMap.forEach((key,value)->{
            wordGo.add(key+"："+value+"次\n","font-size:13;color:#000000;");
        });

        wordGo.add("测试用例执行的具体情况\n","font-size:13;color:#000000;font-weight:bold");
        featureResultInfoMap.forEach((key,value)->{
            wordGo.add(key+"："+value+"\n","font-size:13;color:#000000;");
        });
        wordGo.add("测试场景执行的具体情况\n","font-size:13;color:#000000;font-weight:bold");
        scenarioResultInfoMap.forEach((key,value)->{
            wordGo.add(key+"："+value+"\n","font-size:13;color:#000000;");
        });

        wordGo.add("5.2测试问题统计分析\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add("5.3测试测试结论与建议\n","font-size:13;color:#000000;font-weight:bold");
        wordGo.add(report.getTestConclusion(),"font-size:13;color:#000000;");


        wordGo.create("D:\\notes\\毕业设计\\TestDir\\report.docx");

    }

    public String  checkScenario(JSONObject scenario){
        JSONArray steps = scenario.getJSONArray("steps");
        if(steps.size() == 0){
            return "unknown";
        }
        for(int i = 0;i<steps.size();i++){
            JSONObject step = steps.getJSONObject(i);
            String status = step.getJSONObject("result").getString("status");
            if(!status.equals("passed")){
                return status;
            }
        }
        return "passed";
    }
    public String  checkFeature(JSONObject feature){
        JSONArray elements = feature.getJSONArray("elements");
        for(int i = 0;i<elements.size();i++){
            JSONObject element = elements.getJSONObject(i);
            String status = checkScenario(element);

            if(!status.equals("passed")){
                return status;
            }
        }
        return "passed";
    }



}

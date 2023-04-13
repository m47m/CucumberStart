package com.example.cucumberstart.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 30/03/2023 8:05 am
 */

@Data
@ToString
public class Report {
    private Integer reportId;
    private String templateName;
    private String projectName;
    private String curriculumGroup;
    private String projectIntroduction;
    private String network;
    private String hardware;
    private String software;
    private String testMethod;
    private String scriptGenerationTool;
    private String testConclusion;
    private Long createTime;
    private Long updateTime;
}

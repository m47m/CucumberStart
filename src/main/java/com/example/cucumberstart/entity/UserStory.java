package com.example.cucumberstart.entity;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 28/03/2023 6:05 pm
 */

@Data
@ToString
public class UserStory {
    private Integer userstoryId;
    private String userstoryName;
    private String userstoryContent;

    private String featureName;
    private String featureContent;
    private String featurePath;

    private Long createTime;
    private Long modifyTime;
}

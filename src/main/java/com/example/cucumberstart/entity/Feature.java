package com.example.cucumberstart.entity;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 14/03/2023 8:19 pm
 */
public class Feature {

    private int id;
    private String featureName ;
    private Long lastModified;
    private Long size;
    private String content;

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }



    public Feature(int id_,String featureName_,Long lastModified_,Long size_,String content_){
        this.id = id_;
        this.featureName = featureName_;
        this.content = content_;
        this.lastModified =  lastModified_;
        this.size = size_;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

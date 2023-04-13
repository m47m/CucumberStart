package com.example.cucumberstart.mapper;

import com.example.cucumberstart.entity.Report;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 10/04/2023 4:29 pm
 */

@Repository
@Mapper
public interface ReportMapper {
    void createReport(Report report);
    List<Report> findAllReports();
    void updateReport(Report report);
    void deleteReport(Integer id);

//    void findReport(String templateName);
}

package com.example.cucumberstart.service;

import com.example.cucumberstart.entity.Report;
import com.example.cucumberstart.mapper.ReportMapper;
import com.example.cucumberstart.mapper.UserStoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 7/04/2023 9:15 am
 */
@Service
public class ReportService {
    @Autowired
    ReportMapper reportMapper;


    public String  createReportTemplate(Report report ){
        try{
            reportMapper.createReport(report);
            return "0";
        }catch (Exception e){
            e.printStackTrace();
            return "1";
        }
    }

    public String updateReportTemplate(Report report ){
        try{
            reportMapper.updateReport(report);
            return "0";
        }catch (Exception e){
            e.printStackTrace();
            return "1";
        }
    }

    public String deleteReportTemplate(Integer id){
        try{
            reportMapper.deleteReport(id);
            return "0";
        }catch (Exception e){
            e.printStackTrace();
            return "1";
        }
    }

    public List<Report> findAllReports(){
        return reportMapper.findAllReports();
    }
}

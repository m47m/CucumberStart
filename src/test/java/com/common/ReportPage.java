package com.common;

import com.github.qrpcode.domain.WordGo;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 22/03/2023 10:20 am
 */
public class ReportPage {
    public static void main(String[] args) {


        WordGo wordGo = new WordGo();
//新建一个word
        wordGo.add("Hello World", "font-size: 15; color: #FF0000");
//填充数据可以查看对应功能说明
        wordGo.create("E:\\demo.docx");

//最后生成即可，参数是生成目录，必须带文件名且以.docx结尾

    }
}

package com.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @PROJECT_NAME: CucumberStart
 * @DESCRIPTION:
 * @USER: mhl
 * @DATE: 14/03/2023 2:23 pm
 */
public class LoginPageSteps {

    private  int flag = 1;
//    static {
//        //设置chrome驱动位置
//        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
//    }
//
//    //定义WebDriver对象
//    private final WebDriver driver = new ChromeDriver();

    @Given("打开百度搜索")
    public void open() throws Exception {
//        driver.get("https://www.baidu.com");
//        Thread.sleep(2000);
        System.out.println(this.flag);
    }

    @When("输入 {string}")
    public void searchElement(String query) {
//        WebElement element = driver.findElement(By.name("wd"));
//        // 输入 testingpai
//        element.sendKeys(query);
//        // 点击搜索
//        element.submit();
    }

    @Then("显示 {string}")
    public void check(final String title) {
//        System.out.println("title = " + title);
    }

    @After()
    public void closeBrowser() throws Exception {
//        Thread.sleep(3000);
//        driver.quit();
    }


}

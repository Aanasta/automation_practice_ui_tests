package com.base;

import com.listeners.ScreenshotListener;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.DesiredCapabilities.*;

@Listeners(ScreenshotListener.class)
public abstract class BaseTest {

    protected SoftAssert softAssert;
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        this.driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 1024));
        this.softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup(){
        this.driver.quit();
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}

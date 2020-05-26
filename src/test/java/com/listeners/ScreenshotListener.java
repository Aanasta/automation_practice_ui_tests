package com.listeners;

import com.base.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){
        if(!result.isSuccess()) {
            Object currentTestClass = result.getInstance();
            WebDriver driver = (( BaseTest )currentTestClass).getDriver();
            File screenshot = (( TakesScreenshot ) driver).getScreenshotAs(OutputType.FILE);
            try {
                String screenshotsDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/";
                Files.move(screenshot, new File(screenshotsDirectory + result.getName()
                        + new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date()) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            createAllureAttachment(driver);
            addBrowserConsoleLogs(driver);
        }
    }

    @Attachment(value = "Screenshot")
    private byte[] createAllureAttachment(WebDriver driver) {
        byte[] output;
        try {
            output = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            return output;
        } catch (Exception e) {
            return ("Can not parse screen shot data \n" + e).getBytes();
        }
    }

    @Attachment(value = "Browser Console Logs")
    private String addBrowserConsoleLogs(WebDriver driver) {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        StringBuilder sb = new StringBuilder();
        for (LogEntry logEntry : logEntries.getAll()) {
            sb.append(logEntry.toString());
            sb.append("\n\n");
        }
        return sb.toString();
    }
}

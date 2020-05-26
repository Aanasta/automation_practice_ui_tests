package com.automationpractice.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitManager {

    private WebDriver driver;

    public WaitManager(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementToBeVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(this.driver, 30);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.driver, 30);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public Boolean waitForElementToDisappear(String locator) {
        WebDriverWait wait = new WebDriverWait(this.driver, 30);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
    }
}

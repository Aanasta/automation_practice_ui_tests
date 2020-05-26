package com.automationpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automationpractice.utils.WaitManager;
import io.qameta.allure.Step;

import java.util.List;

public class AbstractPage {

    @FindBy(xpath = "//div[@class='product-container']")
    private WebElement productContainer;

    @FindBy(xpath = "//img[@alt='My Store']")
    private WebElement mainLogo;

    private static final String PRODUCT_CONTAINER_XPATH = "//div[@class='product-container']";

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WaitManager getWaiter() {
        return new WaitManager(this.driver);
    }

    @Step("Check if at least one product is displayed on the page")
    public boolean isProductContainerDisplayed() {
        getWaiter().waitForElementToBeVisible(this.productContainer);
        return this.productContainer.isDisplayed();
    }

    @Step("Check how many products are displayed on the page")
    public int getDisplayedProductsCount() {
        List<WebElement> productContainers = driver.findElements(By.xpath(PRODUCT_CONTAINER_XPATH));
        return productContainers.size();
    }

    @Step("Check current URL of the application")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Step("Open Main page from any other page")
    public MainPage openMainPage() {
        getWaiter().waitForElementToBeClickable(this.mainLogo);
        this.mainLogo.click();
        return new MainPage(driver);
    }
}

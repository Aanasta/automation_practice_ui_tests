package com.automationpractice.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends AbstractPage{

    @FindBy(className = "logout")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[@class='account']//span")
    private WebElement loggedInUsername;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check the username of the logged in user")
    public String getLoggedInUsername() {
        return this.loggedInUsername.getText();
    }

    @Step("Check if the logout button is available")
    public boolean isLogoutButtonDisplayed() {
        return this.logoutButton.isEnabled();
    }
}

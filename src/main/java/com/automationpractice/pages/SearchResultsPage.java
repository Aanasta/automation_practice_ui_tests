package com.automationpractice.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends AbstractPage{

    @FindBy(xpath = "//ul[contains(@class,'product_list')]//a[@class='product-name']")
    private WebElement searchResultName;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check the name of the product displayed in the search results")
    public String getSearchResultName() {
        return this.searchResultName.getText();
    }
}

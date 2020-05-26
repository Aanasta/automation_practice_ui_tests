package com.automationpractice.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CatalogPage extends AbstractPage{

    @FindBy(className = "category-name")
    private WebElement categoryNameText;

    @FindBy(className = "subcategory-image")
    private List<WebElement> subcategoryImages;

    @FindBy(id = "layered_block_left")
    private WebElement filtersBlock;

    @FindBy(className = "layered_filter")
    private List<WebElement> catalogFilters;

    @FindBy(xpath = "//div[@id='layered_price_slider']/a[1]")
    private WebElement lowestPriceSlider;

    @FindBy(xpath = "//div[@id='layered_price_slider']/a[2]")
    private WebElement highestPriceSlider;

    @FindBy(xpath = "//div[@id='layered_price_slider']/div")
    private WebElement priceSlider;

    @FindBy(id = "selectProductSort")
    private WebElement sortByDropdown;

    @FindBy(xpath = "//ul/p/img")
    private WebElement loadingSpinnerImage;

    @FindBy(id = "layered_price_range")
    private WebElement priceRangeValue;

    @FindBy(xpath = "//div[@id='uniform-selectProductSort']/span")
    private WebElement appliedSortingOption;

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check the category name displayed on the banner")
    public String getCategoryNameText() {
        return this.categoryNameText.getText();
    }

    @Step("Check number of subcategories on the Catalog page")
    public int getSubcategoriesCount() {
        return subcategoryImages.size();
    }

    @Step("Check if filters block is displayed")
    public boolean isFiltersBlockDisplayed() {
        return this.filtersBlock.isDisplayed();
    }

    @Step("Check number of filters on the Catalog page")
    public int getFiltersCount() {
        return catalogFilters.size();
    }

    @Step("Set the lowest price in the Price slider")
    //So far the offset for the slider is hardcoded because the slider width changes dynamically after applying the lowest price limit.
    //This will be a point for future enhancement
    public void setPriceFilterByMovingSlider() {
        int offset = (int) (getSliderWidth() * 0.3);
        Actions sliderAction = new Actions(driver);
        sliderAction.dragAndDropBy(lowestPriceSlider, offset, 0).build().perform();
        sliderAction.dragAndDropBy(highestPriceSlider, -offset, 0).build().perform();
    }

    @Step("Set the sorting option for the Catalog page")
    public void setSorting(String sortingOption) {
        Select sortBy = new Select(this.sortByDropdown);
        sortBy.selectByVisibleText(sortingOption);
    }

    @Step("Check if loading spinner is displayed")
    public boolean isLoadingSpinnerDisplayed() {
        return this.loadingSpinnerImage.isDisplayed();
    }

    @Step("Check the image for the loading spinner")
    public String getLoadingSpinnerSrc() {
        return this.loadingSpinnerImage.getAttribute("src");
    }

    @Step("Check the price range set in the Price filter")
    public String getPriceRange() {
        return this.priceRangeValue.getText();
    }

    @Step("Check the sorting option currently applied to the Catalog")
    public String getSorting() {
        return this.appliedSortingOption.getText();
    }

    private int getSliderWidth() {
        getWaiter().waitForElementToBeClickable(priceSlider);
        return this.priceSlider.getSize().getWidth();
    }
}

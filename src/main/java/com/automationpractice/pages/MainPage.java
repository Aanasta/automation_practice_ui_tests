package com.automationpractice.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.automationpractice.config.URL.MAIN_PAGE_URL;

public class MainPage extends AbstractPage{

    @FindBy(xpath = "//ul[@id='homefeatured']//div[@class='product-container']")
    private List<WebElement> popularProducts;

    @FindBy(xpath = "//a[@title='My Store']")
    private WebElement mainLogoLink;

    @FindBy(xpath = "//div[@class='shopping_cart']//b")
    private WebElement shoppingCartButton;

    @FindBy(id = "search_query_top")
    private WebElement searchBarInput;

    @FindBy(xpath = "//a[@title='Women']")
    private WebElement womenTabLabel;

    @FindBy(xpath = "//ul[@id='homefeatured']//img[@title='Faded Short Sleeve T-shirts']")
    private WebElement tshirtContainer;

    @FindBy(xpath = "//ul[@id='homefeatured']//a[@data-id-product='1']//span")
    private WebElement addTshirtToCartButton;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    private WebElement proceedToCheckoutPopupButton;

    @FindBy(xpath = "//ul[@id='homefeatured']//img[@title='Blouse']")
    private WebElement blouseContainer;

    @FindBy(xpath = "//ul[@id='homefeatured']//a[@data-id-product='2']//span")
    private WebElement addBlouseToCartButton;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    private WebElement continueShoppingPopupButton;

    public MainPage(WebDriver driver) {
        super(driver);
        driver.get(MAIN_PAGE_URL);
    }

    @Step("Open Women tab")
    public CatalogPage openWomenTab() {
        this.womenTabLabel.click();
        return new CatalogPage(driver);
    }

    @Step("Check the number of the displayed popular products")
    public int getPopularProductsCount() {
        return this.popularProducts.size();
    }

    @Step("Check if the main logo is displayed")
    public boolean isMainLogoDisplayed() {
        return this.mainLogoLink.isDisplayed();
    }

    @Step("Check if Open shopping cart button is available")
    public boolean isShoppingCartButtonClickable() {
        return this.shoppingCartButton.isEnabled();
    }

    @Step("Check if Search bar is available")
    public boolean isSearchBarAvailable() {
        return this.searchBarInput.isDisplayed();
    }

    @Step("Perform search for a product")
    public SearchResultsPage searchForProduct(String searchedProduct) {
        this.searchBarInput.sendKeys(searchedProduct);
        this.searchBarInput.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    @Step("Add TShirt product and open the Shopping Cart")
    public ShoppingCartPage addTshirtAndGoToShoppingCart() {
        hoverTshirtProductContainer();
        clickAddTshirtToCartButton();
        clickProceedToCheckoutPopupButton();
        return new ShoppingCartPage(driver);
    }

    @Step("Add a blouse to cart anf click Continue shopping button")
    public void addBlouseAndContinueShopping() {
        hoverBlouseProductContainer();
        clickAddBlouseToCartButton();
        clickContinueShoppingPopupButton();
    }

    private void hoverBlouseProductContainer() {
        getWaiter().waitForElementToBeClickable(this.blouseContainer);
        Actions builder = new Actions(driver);
        builder.moveToElement(blouseContainer).build().perform();
    }

    private void clickAddBlouseToCartButton() {
        getWaiter().waitForElementToBeClickable(this.addBlouseToCartButton);
        this.addBlouseToCartButton.click();
    }

    private void clickContinueShoppingPopupButton() {
        getWaiter().waitForElementToBeClickable(this.continueShoppingPopupButton);
        this.continueShoppingPopupButton.click();
    }

    private void hoverTshirtProductContainer() {
        getWaiter().waitForElementToBeClickable(this.tshirtContainer);
        Actions builder = new Actions(driver);
        builder.moveToElement(tshirtContainer).build().perform();
    }

    private void clickAddTshirtToCartButton() {
        getWaiter().waitForElementToBeClickable(this.addTshirtToCartButton);
        this.addTshirtToCartButton.click();
    }

    private void clickProceedToCheckoutPopupButton() {
        getWaiter().waitForElementToBeClickable(this.proceedToCheckoutPopupButton);
        this.proceedToCheckoutPopupButton.click();
    }
}

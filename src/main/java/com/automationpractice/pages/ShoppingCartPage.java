package com.automationpractice.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.automationpractice.testdata.TestdataShoppingCartPage.EXCEL_PRICELIST_PATH;
import static com.automationpractice.utils.ExcelManager.getDataFromCell;

public class ShoppingCartPage extends AbstractPage{

    @FindBy(xpath = "//p[contains(@class,'cart_navigation')]//span")
    private WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//input[@id='cgv']")
    private WebElement agreeToTermsCheckbox;

    @FindBy(xpath = "//a[@class='bankwire']")
    private WebElement bankwirePaymentButton;

    @FindBy(xpath = "//p[@id='cart_navigation']//button")
    private WebElement confirmOrderButton;

    @FindBy(xpath = "//p[@class='cheque-indent']/strong")
    private WebElement orderConfirmationMessage;

    @FindBy(xpath = "//span[@class='price']/strong")
    private WebElement orderAmount;

    @FindBy(xpath = "//a[@title='Back to orders']")
    private WebElement backToOrdersButton;

    @FindBy(xpath = "//span[@class='ajax_cart_quantity unvisible']")
    private WebElement productsInCartCounter;

    @FindBy(name = "quantity_2_7_0_0")
    private WebElement blouseQuantityInput;

    @FindBy(className = "icon-plus")
    private WebElement plusQuantityButton;

    @FindBy(xpath = "//p/a[.='Blouse']")
    private WebElement blouseInCartTitle;

    @FindBy(id = "total_product_price_2_7_0")
    private WebElement totalPriceForBlouse;

    @FindBy(xpath = "//p/a[.='Faded Short Sleeve T-shirts']")
    private WebElement tshirtInCartTitle;

    @FindBy(id = "total_product_price_1_1_0")
    private WebElement totalPriceForTshirt;

    @FindBy(id = "total_price")
    private WebElement totalPriceForCart;

    @FindBy(xpath = "//a[@id='cart_quantity_down_1_1_0_0']//i")
    private WebElement tshirtQuantityMinusButton;

    private static final String TSHIRT_QUANTITY_MINUS_BUTTON = "//a[@id='cart_quantity_down_1_1_0_0']//i";

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Navigate through the Purchase form")
    public void completePurchaseByLoggedinUser() {
        clickProceedToCheckout(); //in the Summary tab
        clickProceedToCheckout(); //in the Address tab
        checkTermsCheckbox(); //in the Shipping tab
        clickProceedToCheckout(); //in the Shipping tab
        clickBankwirePaymentButton(); //in the Payment tab
        clickConfirmOrderButton(); //in the Payment tab
    }

    @Step("Check the Order Confirmation Message")
    public String getOrderConfirmationMessage() {
        return this.orderConfirmationMessage.getText();
    }

    @Step("Check the total amount of the order")
    public String getOrderAmount() {
        getWaiter().waitForElementToBeVisible(this.orderAmount);
        return this.orderAmount.getText();
    }

    @Step("Check if Back to orders button is available")
    public boolean isBackToOrdersButtonAvailable() {
        return this.backToOrdersButton.isEnabled();
    }

    @Step("Check the amount of products in the Shopping cart")
    public String getProductsInCartCount() {
        return this.productsInCartCounter.getAttribute("innerText");
    }

    @Step("Set quantity of ordered products (blouse) by entering the value from the keyboard")
    public void setBlouseQuantityFromKeyboard(String cellAddress) {
        this.blouseQuantityInput.clear();
        String editedQuantityForBlouse = getDataFromCell(EXCEL_PRICELIST_PATH, cellAddress);
        this.blouseQuantityInput.sendKeys(editedQuantityForBlouse);
    }

    @Step("Check quantity of ordered blouses in the Shopping Cart")
    public String getBlousesQuantity() {
        return this.blouseQuantityInput.getAttribute("value");
    }

    @Step("Check if blouse is added to the cart")
    public boolean isBlouseAddedToTheCart() {
        return this.blouseInCartTitle.isDisplayed();
    }

    @Step("Check total price for the ordered blouses in the cart")
    public String getTotalPriceForBlouse() {
        return this.totalPriceForBlouse.getText();
    }

    @Step("Check if tShirt is added to the cart")
    public boolean isTshirtAddedToTheCart() {
        try {
            return this.tshirtInCartTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Check total price for the ordered tShirts in the cart")
    public String getTotalPriceForTshirt() {
        return this.totalPriceForTshirt.getText();
    }

    @Step("Get total price for the Shopping Cart")
    public String getTotalPriceForCart() {
        return this.totalPriceForCart.getText();
    }

    @Step("Set quantity of tShirts by clicking Minus button")
    public void setTshirtQuantityByMinusButton() {
        this.tshirtQuantityMinusButton.click();
        getWaiter().waitForElementToDisappear(TSHIRT_QUANTITY_MINUS_BUTTON);
    }

    @Step("Click Proceed to checkout button to navigate between Shopping Cart tabs")
    private void clickProceedToCheckout() {
        getWaiter().waitForElementToBeClickable(this.proceedToCheckoutButton);
        this.proceedToCheckoutButton.click();
    }

    @Step("Check I agree Terms of service checkbox")
    private void checkTermsCheckbox() {
        clickByElementViaJS(this.agreeToTermsCheckbox);
    }

    private  void clickByElementViaJS(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) this.driver;
        jse.executeScript("arguments[0].click();", element);
    }

    @Step("Click Pay by bank wire payment option")
    private void clickBankwirePaymentButton() {
        getWaiter().waitForElementToBeClickable(this.bankwirePaymentButton);
        this.bankwirePaymentButton.click();
    }

    @Step("Click I confirm my order button")
    private void clickConfirmOrderButton() {
        this.confirmOrderButton.click();
    }
}

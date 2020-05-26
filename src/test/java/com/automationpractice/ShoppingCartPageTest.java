package com.automationpractice;

import com.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import com.automationpractice.pages.AccountPage;
import com.automationpractice.pages.LoginPage;
import com.automationpractice.pages.MainPage;
import com.automationpractice.pages.ShoppingCartPage;

import static com.automationpractice.testdata.TestdataLoginPage.VALID_USER_EMAIL;
import static com.automationpractice.testdata.TestdataLoginPage.VALID_USER_PASSWORD;
import static com.automationpractice.testdata.TestdataShoppingCartPage.*;
import static com.automationpractice.utils.ExcelManager.getDataFromCell;

@Epic("Base UI tests")
@Feature("Shopping Cart")
public class ShoppingCartPageTest extends BaseTest {

    @Test
    @Story("Verify product is successfully ordered by the logged in user")
    public void productIsOrderedByLoggedInUserTest() {

        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = loginPage.loginToProfile(VALID_USER_EMAIL, VALID_USER_PASSWORD);
        MainPage mainPage = accountPage.openMainPage();
        ShoppingCartPage shoppingCartPage = mainPage.addTshirtAndGoToShoppingCart();
        shoppingCartPage.completePurchaseByLoggedinUser();

        softAssert.assertEquals(shoppingCartPage.getOrderConfirmationMessage(), ORDER_CONFIRMATION_MESSAGE, "Order is not completed");
        softAssert.assertEquals(shoppingCartPage.getOrderAmount(), ORDER_AMOUNT, "Order amount is not as expected");
        softAssert.assertTrue(shoppingCartPage.isBackToOrdersButtonAvailable(), "Back to orders button is not available");
        softAssert.assertEquals(shoppingCartPage.getProductsInCartCount(), PRODUCTS_IN_CART_COUNT, "There are still products in the Shopping Cart");
    }

    @Test
    @Story("Verify products in the Shopping Cart are successfully edited")
    // Please note the test might be unstable because the performance of the website may be very low at times")
    public void totalPricesAreEditedInTheShoppingCartTest() {
        String totalPriceForBlouseBeforeEdit = getDataFromCell(EXCEL_PRICELIST_PATH, "C2");
        String totalPriceForTshirtBeforeEdit = getDataFromCell(EXCEL_PRICELIST_PATH, "C3");
        String totalPriceForCartBeforeEdit = getDataFromCell(EXCEL_PRICELIST_PATH, "C4");
        String editedQuantityForBlouse = getDataFromCell(EXCEL_PRICELIST_PATH, "D2");
        String totalPriceForBlouseAfterEdit = getDataFromCell(EXCEL_PRICELIST_PATH, "E2");
        String totalPriceForCartAfterEdit = getDataFromCell(EXCEL_PRICELIST_PATH, "E4");

        MainPage mainPage = new MainPage(driver);
        mainPage.addBlouseAndContinueShopping();
        ShoppingCartPage shoppingCartPage = mainPage.addTshirtAndGoToShoppingCart();

        softAssert.assertTrue(shoppingCartPage.isBlouseAddedToTheCart(), "Blouse product is not added to the Cart");
        softAssert.assertEquals(shoppingCartPage.getTotalPriceForBlouse(), totalPriceForBlouseBeforeEdit, "Total price for the added blouse is not calculated as expected");
        softAssert.assertTrue(shoppingCartPage.isTshirtAddedToTheCart(), "TShirt product is not added to the Cart");
        softAssert.assertEquals( shoppingCartPage.getTotalPriceForTshirt(), totalPriceForTshirtBeforeEdit, "Total price for the added tShirt is not calculated as expected");
        softAssert.assertEquals(shoppingCartPage.getTotalPriceForCart(), totalPriceForCartBeforeEdit, "Total price for the Cart is not calculated as expected");

        shoppingCartPage.setBlouseQuantityFromKeyboard("D2");
        shoppingCartPage.setTshirtQuantityByMinusButton();

        softAssert.assertEquals(shoppingCartPage.getBlousesQuantity(), editedQuantityForBlouse, "Quantity was not set from keyboard as expected");
        softAssert.assertFalse(shoppingCartPage.isTshirtAddedToTheCart(), "Quantity was not set to 0 and the product was not deleted");
        softAssert.assertEquals(shoppingCartPage.getTotalPriceForBlouse(), totalPriceForBlouseAfterEdit, "Total price for the edited products quantity is not calculated as expected");
        softAssert.assertEquals(shoppingCartPage.getTotalPriceForCart(), totalPriceForCartAfterEdit, "Total price for the Cart is not calculated as expected after editing products");
    }
}

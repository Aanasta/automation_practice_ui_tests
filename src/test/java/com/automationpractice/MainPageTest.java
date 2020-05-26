package com.automationpractice;

import com.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import com.automationpractice.pages.MainPage;

import static com.automationpractice.testdata.TestdataGeneral.EXPECTED_PRODUCTS_COUNT;

@Epic("Base UI tests")
@Feature("Main Page")
public class MainPageTest extends BaseTest {

    @Test
    @Story("Verify Main page is opened and the website is available")
    public void mainPageIsOpenedTest() {
        MainPage mainPage = new MainPage(driver);

        softAssert.assertTrue(mainPage.isProductContainerDisplayed(), "Products are not displayed on the Main page");
        softAssert.assertEquals(mainPage.getPopularProductsCount(), EXPECTED_PRODUCTS_COUNT, "Number of products in Popular tab is not as expected");
        softAssert.assertTrue(mainPage.isMainLogoDisplayed(), "Main logo is not displayed");
        softAssert.assertTrue(mainPage.isShoppingCartButtonClickable(), "Shopping cart button is not clickable");
        softAssert.assertTrue(mainPage.isSearchBarAvailable(), "Search bar is not available");
        softAssert.assertAll();
    }
}

package com.automationpractice;

import com.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import com.automationpractice.pages.CatalogPage;
import com.automationpractice.pages.MainPage;

import static com.automationpractice.config.URL.CATALOG_PAGE_URL;
import static com.automationpractice.testdata.TestdataCatalogPage.*;
import static com.automationpractice.testdata.TestdataGeneral.EXPECTED_PRODUCTS_COUNT;

@Epic("Base UI tests")
@Feature("Catalog")
public class CatalogPageTest extends BaseTest {

    @Test
    @Story("Verify Catalog page is opened successfully")
    public void catalogPageIsOpenedTest() {
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = mainPage.openWomenTab();

        softAssert.assertEquals(driver.getCurrentUrl(), CATALOG_PAGE_URL, "Catalog page is not opened");
        softAssert.assertTrue(catalogPage.isProductContainerDisplayed(), "Products are not displayed on the Catalog");
        softAssert.assertEquals(catalogPage.getDisplayedProductsCount(), EXPECTED_PRODUCTS_COUNT, "Catalog products count is not as expected" );
        softAssert.assertEquals(catalogPage.getCategoryNameText(), EXPECTED_CATEGORY_NAME, "Catalog tab name is not as expected");
        softAssert.assertEquals(catalogPage.getSubcategoriesCount(), EXPECTED_SUBCATEGORY_COUNT, "Number of subcategories is not expected");
        softAssert.assertTrue(catalogPage.isFiltersBlockDisplayed(), "Filters are not displayed");
        softAssert.assertEquals(catalogPage.getFiltersCount(), EXPECTED_FILTERS_COUNT, "Number of filters is not as expected");
        softAssert.assertAll();
    }

    @Test
    @Story("Verify the Catalog page is successfully filtered and sorted")
    // Please note the logic for providing filtered products is not implemented on the website so we check UI side only
    public void catalogPageIsFilteredAndSortedTest() {
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = mainPage.openWomenTab();
        catalogPage.setPriceFilterByMovingSlider();
        catalogPage.setSorting(EXPECTED_SORTING_OPTION);

        softAssert.assertTrue(catalogPage.isLoadingSpinnerDisplayed(), "Loading spinner is not displayed after applying filter and sorting");
        softAssert.assertEquals(catalogPage.getLoadingSpinnerSrc(), LOADING_SPINNER_EXPECTED_SRC, "Loading spinner image source is not as expected");
        softAssert.assertEquals(catalogPage.getPriceRange(), EXPECTED_PRICE_RANGE, "Price filter is not set as expected");
        softAssert.assertEquals(catalogPage.getSorting(), EXPECTED_SORTING_OPTION, "Sorting option is not set as expected");
        softAssert.assertAll();
    }
}

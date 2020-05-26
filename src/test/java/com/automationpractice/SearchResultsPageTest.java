package com.automationpractice;

import com.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import com.automationpractice.pages.MainPage;
import com.automationpractice.pages.SearchResultsPage;

import static com.automationpractice.config.URL.SEARCH_RESULTS_PAGE_URL;
import static com.automationpractice.testdata.TestdataSearchResultsPage.EXPECTED_SEARCH_RESULTS_COUNT;
import static com.automationpractice.testdata.TestdataSearchResultsPage.PRODUCT_SEARCH_QUERY;

@Epic("Base UI tests")
@Feature("Search Results")
public class SearchResultsPageTest extends BaseTest {

    @Test
    @Story("Verify Search is working and search results are displayed as expected")
    public void searchIsWorkingTest() {
        MainPage mainPage = new MainPage(driver);
        SearchResultsPage searchResultsPage = mainPage.searchForProduct(PRODUCT_SEARCH_QUERY);

        softAssert.assertEquals(searchResultsPage.getCurrentUrl(), SEARCH_RESULTS_PAGE_URL, "Search results page is not opened");
        softAssert.assertTrue(searchResultsPage.isProductContainerDisplayed(), "Search results are not displayed");
        softAssert.assertEquals(searchResultsPage.getDisplayedProductsCount(), EXPECTED_SEARCH_RESULTS_COUNT, "Number of search results is not as expected");
        softAssert.assertEquals(searchResultsPage.getSearchResultName(), PRODUCT_SEARCH_QUERY, "Search is not working as expected");
        softAssert.assertAll();
    }
}

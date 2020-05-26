package com.automationpractice;

import com.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import com.automationpractice.pages.AccountPage;
import com.automationpractice.pages.LoginPage;

import static com.automationpractice.config.URL.ACCOUNT_PAGE_URL;
import static com.automationpractice.testdata.TestdataLoginPage.*;

@Epic("Base UI tests")
@Feature("Login")
public class LoginPageTest extends BaseTest {

    @Test
    @Story("Verify a registered user successfully logs into the platform")
    public void registeredUserLogsInTest() {
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = loginPage.loginToProfile(VALID_USER_EMAIL, VALID_USER_PASSWORD);

        softAssert.assertEquals(driver.getCurrentUrl(), ACCOUNT_PAGE_URL, "Account page isn't opened");
        softAssert.assertEquals(accountPage.getLoggedInUsername(), VALID_USER_NAME, "Expected user is not logged in");
        softAssert.assertTrue(accountPage.isLogoutButtonDisplayed(), "Logout button is not displayed");
        softAssert.assertAll();
    }

    @Test
    @Story("Verify a new user is successfully registered on the platform")
    public void userIsRegisteredTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registerEmail();
        loginPage.fillPersonalData(NEW_USER_FIRST_NAME, NEW_USER_LAST_NAME, NEW_USER_PASSWORD);
        loginPage.fillAddressData(NEW_USER_ADDRESS, NEW_USER_CITY, NEW_USER_STATE, NEW_USER_POSTAL_CODE, NEW_USER_COUNTRY, NEW_USER_PHONE);
        AccountPage accountPage = loginPage.registerNewUser();

        softAssert.assertEquals(driver.getCurrentUrl(), ACCOUNT_PAGE_URL, "Registration was not completed successfully");
        softAssert.assertEquals(accountPage.getLoggedInUsername(), NEW_USER_NAME, "Registered user is not logged in");
        softAssert.assertTrue(accountPage.isLogoutButtonDisplayed(), "Logout button is not displayed");
        softAssert.assertAll();
    }
}

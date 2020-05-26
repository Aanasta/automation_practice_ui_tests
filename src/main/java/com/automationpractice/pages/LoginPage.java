package com.automationpractice.pages;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static com.automationpractice.config.URL.LOGIN_PAGE_URL;

public class LoginPage extends AbstractPage {

    @FindBy(id = "email")
    private WebElement registeredEmailInput;

    @FindBy(id = "passwd")
    private WebElement passwordInput;

    @FindBy(id = "SubmitLogin")
    private WebElement loginButton;

    @FindBy(id = "email_create")
    private WebElement emailForRegistrationInput;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountButton;

    @FindBy(xpath = "//label[@for='id_gender1']")
    private WebElement maleGenderButton;

    @FindBy(id = "customer_firstname")
    private WebElement firstNameInput;

    @FindBy(id = "customer_lastname")
    private WebElement lastNameInput;

    @FindBy(id = "address1")
    private WebElement addressInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "id_state")
    private WebElement stateDropdown;

    @FindBy(id = "postcode")
    private WebElement postalCodeInput;

    @FindBy(id = "id_country")
    private WebElement countryDropdown;

    @FindBy(id = "phone_mobile")
    private WebElement mobilePhoneInput;

    @FindBy(id = "submitAccount")
    private WebElement registerButton;


    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(LOGIN_PAGE_URL);
    }

    @Step("Log in to the user profile")
    public AccountPage loginToProfile(String email, String password) {
        fillRegisteredEmail(email);
        fillPassword(password);
        this.loginButton.click();
        return new AccountPage(driver);
    }

    private void fillRegisteredEmail(String email) {
        this.registeredEmailInput.sendKeys(email);
    }

    private void fillPassword(String password) {
        this.passwordInput.sendKeys(password);
    }

    @Step("Fill the email to start registration process")
    public void registerEmail() {
        this.emailForRegistrationInput.sendKeys(generateRegistrationEmail());
        this.createAccountButton.click();
    }

    private String generateRegistrationEmail() {
        String generatedString = RandomStringUtils.randomAlphabetic(8);
        return (generatedString + "@gmail.com");
    }

    @Step("Fill the registration form with personal data")
    public void fillPersonalData(String firstName, String lastName, String password) {
        getWaiter().waitForElementToBeClickable(this.maleGenderButton);
        this.maleGenderButton.click();
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
        this.passwordInput.sendKeys(password);
    }

    @Step("Fill the registration form with address data")
    public void fillAddressData(String address, String city, String state, String postalCode, String country, String phone) {
        this.addressInput.sendKeys(address);
        this.cityInput.sendKeys(city);
        selectStateFromDropdown(state);
        this.postalCodeInput.sendKeys(postalCode);
        selectCountryFromDropdown(country);
        this.mobilePhoneInput.sendKeys(phone);
    }

    @Step("Click Register New User button")
    public AccountPage registerNewUser() {
        this.registerButton.click();
        return new AccountPage(driver);
    }

    private void selectStateFromDropdown(String state) {
        Select stateFromDropdown = new Select(this.stateDropdown);
        stateFromDropdown.selectByVisibleText(state);
    }

    private void selectCountryFromDropdown(String country) {
        Select stateFromDropdown = new Select(this.countryDropdown);
        stateFromDropdown.selectByVisibleText(country);
    }
}

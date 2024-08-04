package lib.ui;

import io.qameta.allure.Epic;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private static final String
        LOGIN_BUTTON = "xpath:(//span[text()='Log in'])[2]",
        LOGIN_INPUT = "xpath://input[@id='wpName1']",
        PASSWORD_INPUT = "xpath://input[@id='wpPassword1']",
        SUBMIT_BUTTON = "xpath://button[@id='wpLoginAttempt']";


    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find login button", 15);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click login button", 5);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put login", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put password", 5);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click SUBMIT_BUTTON", 5);
    }
}

package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class HomePageObject extends MainPageObject {
    public HomePageObject(RemoteWebDriver driver) {
        super(driver);
    }
    protected static String
            MAIN_TOOLBAR;

    public void isMainPageDisplayed() {
        this.waitForElementPresent(MAIN_TOOLBAR, "Cannot find MAIN_TOOLBAR, home page is not displayed", 10);
    }
}

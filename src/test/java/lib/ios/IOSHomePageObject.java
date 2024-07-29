package lib.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.HomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSHomePageObject extends HomePageObject {

    static {
        MAIN_TOOLBAR = "xpath://XCUIElementTypeToolbar[@name='Toolbar']";
    }

    public IOSHomePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

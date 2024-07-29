package lib.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.HomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidHomePageObject extends HomePageObject {

    static {
        MAIN_TOOLBAR = "id:org.wikipedia:id/main_toolbar";
    }

    public AndroidHomePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

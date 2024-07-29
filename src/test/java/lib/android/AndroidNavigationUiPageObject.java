package lib.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUiPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUiPageObject extends NavigationUiPageObject {

    static {
        MY_LISTS_LINK = "id:org.wikipedia:id/nav_tab_reading_lists";
    }

    public AndroidNavigationUiPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

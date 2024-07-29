package lib.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUiPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUi extends NavigationUiPageObject {

    static {
        MY_LISTS_LINK = "id:Saved";
    }


    public IOSNavigationUi(RemoteWebDriver driver) {
        super(driver);
    }
}

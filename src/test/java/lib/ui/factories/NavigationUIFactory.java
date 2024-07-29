package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.android.AndroidNavigationUiPageObject;
import lib.ios.IOSNavigationUi;
import lib.ui.NavigationUiPageObject;
import lib.ui.mobile_web.MWNavigationUiPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory {

    public static NavigationUiPageObject get(RemoteWebDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUiPageObject(driver);
        } else if(Platform.getInstance().isIOS()) {
            return new IOSNavigationUi(driver);
        } else {
            return new MWNavigationUiPageObject(driver);
        }
    }
}

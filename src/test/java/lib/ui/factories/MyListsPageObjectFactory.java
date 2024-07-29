package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.android.AndroidMyListsPageObject;
import lib.ios.IOSMyListsPageObject;
import lib.ui.MyListsPageObject;
import lib.ui.mobile_web.MWMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(RemoteWebDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else if(Platform.getInstance().isIOS()) {
            return new IOSMyListsPageObject(driver);
        } else {
            return new MWMyListsPageObject(driver);
        }
    }
}

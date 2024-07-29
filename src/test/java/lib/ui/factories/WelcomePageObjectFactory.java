package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.android.AndroidSearchPageObject;
import lib.android.AndroidWelcomePageObject;
import lib.ios.IOSSearchPageObject;
import lib.ios.IOSWelcomePageObject;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObjectFactory {
    public static WelcomePageObject get(RemoteWebDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidWelcomePageObject(driver);
        } else {
            return new IOSWelcomePageObject(driver);
        }
    }
}

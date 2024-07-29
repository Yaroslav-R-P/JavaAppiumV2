package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.android.AndroidSearchPageObject;
import lib.ios.IOSSearchPageObject;
import lib.ui.SearchPageObject;
import lib.ui.mobile_web.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.crypto.SealedObject;

public class SearchPageObjectFactory {

    public static SearchPageObject get(RemoteWebDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSSearchPageObject(driver);
        } else {
            return new MWSearchPageObject(driver);}
        }
}

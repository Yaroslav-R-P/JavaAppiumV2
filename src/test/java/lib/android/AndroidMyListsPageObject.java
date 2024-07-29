package lib.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_NAME_TPL = "{FOLDER_NAME}";
        ARTICLE_BY_TITLE_TPL = "xpath://android.widget.TextView[@text='{TITLE}']";
        NOT_NOW_LINK = "";
    }


    public AndroidMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

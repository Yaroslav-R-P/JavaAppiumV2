package lib.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_NAME_TPL = "{FOLDER_NAME}";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        NOT_NOW_LINK = "xpath://XCUIElementTypeButton[@name='Close']";
        DELETE_ARTICLE = "id:swipe action delete";
        JAVA_ARTICLE_TITLE = "xpath://XCUIElementTypeStaticText[@name='Java (programming language)']";
        SAVED_ARTICLE_ELEMENT = "xpath://XCUIElementTypeCell";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}

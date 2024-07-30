package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_NAME_TPL = "{FOLDER_NAME}";
        ARTICLE_BY_TITLE_TPL = "xpath://li[contains(@class,'page-summary with-watchstar')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://li[contains(@class,'page-summary with-watchstar')]//h3[contains(text(),'{TITLE}')]/../../a[contains(@aria-controls,'mw-watchlink-notification')]";
        NOT_NOW_LINK = "xpath://XCUIElementTypeButton[@name='Close']";
        DELETE_ARTICLE = "id:swipe action delete";
        JAVA_ARTICLE_TITLE = "xpath://XCUIElementTypeStaticText[@name='Java (programming language)']";
        SAVED_ARTICLE_ELEMENT = "xpath://li[@class='page-summary with-watchstar']";


    }


    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

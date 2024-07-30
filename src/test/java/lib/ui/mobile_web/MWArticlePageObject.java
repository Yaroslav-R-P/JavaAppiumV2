package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE_TPL = "xpath://span[@class='mw-page-title-main'][contains(text(),'{TITLE_TEXT}')]";
        SAVE_BUTTON = "xpath://a[@id='ca-watch']";
        ANDROID_TITLE_ELEMENT_FOR_TESTS = "xpath://span[@class='mw-page-title-main']";
        GO_HOME_LINK = "xpath:(//a[@href='/wiki/Main_Page'])[2]";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://a[@id='ca-watch']";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://span[@class='minerva-icon minerva-icon--unStar-progressive']";
        CLOSE_SEARCH = "xpath:(//span[@class='mf-icon mf-icon-close '])[2]";
    }


    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://button[@id='searchIcon']";
        SEARCH_INPUT = "xpath://input[@class='search mf-icon-search']";
        SEARCH_CANCEL_BUTTON = "xpath://span[@class='mf-icon mf-icon-clear mf-icon--small ']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://li[@title='{SUBSTRING}']";
        SEARCH_RESULT_ELEMENT = "xpath://li[@class='page-summary']";
        SEARCH_EMPTY_RESULT = "xpath://p[@class='without-results']"; //когда поиск не дал результатов
        SEARCH_EMPTY_PAGE_LOGO = "xpath://span[@class='mf-icon mf-icon-articlesSearch ']"; // лого вики, когда поиск сброшен в 0 и страница пуста
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

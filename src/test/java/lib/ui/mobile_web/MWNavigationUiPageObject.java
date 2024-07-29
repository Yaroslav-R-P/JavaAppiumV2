package lib.ui.mobile_web;

import lib.ui.NavigationUiPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUiPageObject extends NavigationUiPageObject {

    static {
        MY_LISTS_LINK = "xpath://span[@class='toggle-list-item__label'][contains(text(),'Watch')]";
        OPEN_NAVIGATION = "xpath://label[@for='main-menu-input']";
    }



    public MWNavigationUiPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

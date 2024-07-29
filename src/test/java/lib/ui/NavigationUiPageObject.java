package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUiPageObject extends MainPageObject {

    protected static String
        MY_LISTS_LINK,
        OPEN_NAVIGATION;

     public void openMyLists() {
         if(Platform.getInstance().isMw()) {
             this.triClickElementWithFewAttempts(MY_LISTS_LINK,"Cannot find MY_LISTS_LINK element",10);
         }
         this.waitForElementAndClick(MY_LISTS_LINK, "Cannot find MY_LISTS_LINK element", 5);
     }
     public void openNavigation() {
         if(Platform.getInstance().isMw()) {
             this.waitForElementAndClick(OPEN_NAVIGATION,"Cannot find and click OPEN_NAVIGATION button", 5);
         } else {
             System.out.println("Method openNavigation do nothing for platform " + Platform.getInstance().getPlatformVar());
         }
     }

    public void goToLists(){
        driver.get("https://en.m.wikipedia.org/wiki/Special:EditWatchlist");
    }

    public NavigationUiPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

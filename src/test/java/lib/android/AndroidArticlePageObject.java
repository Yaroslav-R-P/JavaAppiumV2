package lib.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

     static {
         TITLE_TPL = "xpath:(//android.widget.TextView[@text='{TITLE_TEXT}'])[1]"; //TITLE = "(//android.widget.TextView[@class='android.widget.TextView'])[1]",
         SAVE_BUTTON = "id:org.wikipedia:id/page_save";
         OK_BUTTON_IN_THE_SAVE_WINDOW = "id:android:id/button1";
         ADD_TO_LIST_BUTTON_IN_SNACKBAR = "id:org.wikipedia:id/snackbar_action";
         VIEW_LIST_BUTTON_IN_SNACKBAR = "id:org.wikipedia:id/snackbar_action";
         MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
         ANDROID_TITLE_ELEMENT_FOR_TESTS = "xpath://android.widget.TextView[@text='Java (programming language)']";
         CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
         CLOSE_SEARCH = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
     }

     public AndroidArticlePageObject(RemoteWebDriver driver) {
         super(driver);
     }




}

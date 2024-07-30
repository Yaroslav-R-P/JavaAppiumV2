package lib.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_NAME_TPL = "{FOLDER_NAME}";
        ARTICLE_BY_TITLE_TPL = "xpath://android.widget.TextView[@text='{TITLE}']";
        SAVED_ARTICLES_LINK = "id:org.wikipedia:id/item_description";
        SAVED_ARTICLE_ELEMENT = "xpath://androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/reading_list_recycler_view']/android.widget.FrameLayout";
        BACK_LINK_TO_SAVED = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";

    }


    public AndroidMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

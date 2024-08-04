package lib.ui;

import io.qameta.allure.Epic;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {
    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    protected static String
    FOLDER_NAME_TPL,
    ARTICLE_BY_TITLE_TPL,

    NOT_NOW_LINK,
    BACK_LINK_TO_SAVED,
    DELETE_ARTICLE,
    JAVA_ARTICLE_TITLE,
    REMOVE_FROM_SAVED_BUTTON,
    SAVED_ARTICLES_LINK,
    SAVED_ARTICLE_ELEMENT;


    // данный метод написан для примера, не используется в новой версии вики
    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

// данный метод написан для примера, не используется в новой версии вики
//    public void openFolderByName(String name_of_folder) {
//        this.waitForElementAndClick(By.linkText(name_of_folder),
//                "Cannot find by name " + name_of_folder,
//        5);
//    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    article_xpath,
                    600,
                    "Cannot to swipe"
            );
            this.waitForArticleToDisappearByTitle(article_title);
        } else {
           String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator, "Cannot find and click button to remuve article frome saved", 15);
            driver.navigate().refresh();
        }

    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }
    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article title still present with title" + article_title, 15);
    }

    public void closeSynchronizationWindow() {
        waitForElementAndClick(NOT_NOW_LINK, "Cannot find close button on synchronization window", 10);
    }

    public void swipeAndTapDeleteArticle() {
        this.swipeElementToLeft(
                JAVA_ARTICLE_TITLE,
                600,
                "Cannot to swipe"
        );
        this.waitForElementAndClick(DELETE_ARTICLE, "Cannot find Delete button", 10);
    }

    public void openSavedArticles() {
        waitForElementAndClick(SAVED_ARTICLES_LINK, "Cannot find and click SAVED_ARTICLES_LINK", 10);
    }

    public void closeSavedArticles() {
        waitForElementAndClick(BACK_LINK_TO_SAVED, "Cannot find and click BACK_LINK_TO_SAVED", 10);
    }

    public int getAmountOfSavedArticles() {
        this.waitForElementPresent(
                SAVED_ARTICLE_ELEMENT,
                "Cannot find by the request",
                20
        );
        return this.getAmountOfElements(SAVED_ARTICLE_ELEMENT);
    }


}

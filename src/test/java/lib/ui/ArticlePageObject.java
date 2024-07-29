package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
    TITLE_TPL,
    SAVE_BUTTON,
    OK_BUTTON_IN_THE_SAVE_WINDOW,
    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
    OPTIONS_ADD_TO_MY_LIST_BUTTON,
    ADD_TO_LIST_BUTTON_IN_SNACKBAR,
    VIEW_LIST_BUTTON_IN_SNACKBAR,
    MY_LIST_NAME_INPUT,
    ANDROID_TITLE_ELEMENT_FOR_TESTS,
    CLOSE_ARTICLE_BUTTON,
    GO_HOME_LINK;


    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getTitleXpathByArticleName(String articleNameWithSubstring) {
        return TITLE_TPL.replace("{TITLE_TEXT}", articleNameWithSubstring);
    }

    public WebElement waitForTitleElement(String articleWithSubstring) {
        String titleXpath = getTitleXpathByArticleName(articleWithSubstring);
        return this.waitForElementPresent(titleXpath, "Cannot find article title on page", 20);
    }
    /*
    Ниже пример изменения методов в зависимости от типа платфрмы.По факту, логика на обе платформы у тебя одинакова +-, ты понял, но как пример
    стоит учитывать. Дальше методы будут переписываться в зависимости от платфрмы
     */
    public String getArticleTitle() {
        WebElement element = waitForElementPresent(ANDROID_TITLE_ELEMENT_FOR_TESTS,"cannot find article title", 20);
        if(Platform.getInstance().isAndroid()) {
            return element.getText();
        } else if(Platform.getInstance().isIOS()) {
            return element.getAttribute("name");
        } else {
            return element.getText();
        }
    }

    public void addArticleToMyListAndGoToIt(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find save page element",
                5);

        this.waitForElementAndClick(
               ADD_TO_LIST_BUTTON_IN_SNACKBAR,
                "Cannot find 'Add to list' button on snackbar",
                5);

        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                10
        );

        this.waitForElementAndClick(
                OK_BUTTON_IN_THE_SAVE_WINDOW,
                "Cannot find 'Ok' button",
                5);

        this.waitForElementAndClick(
                VIEW_LIST_BUTTON_IN_SNACKBAR,
                "Cannot find 'Ok' button on snackbar_action",
                5);
    }

    public void assertTheArticleHasATitle(String articleNameWithSubstring) {
    String title_xpath = getTitleXpathByArticleName(articleNameWithSubstring);
        this.waitForElementPresent(title_xpath, "The article has no title", 10);
    }

    public void addArticlesToMySave() {
    if(Platform.getInstance().isMw()) {
        this.removeArticleFromSavedIfItAdded();
        this.waitForElementAndClick(SAVE_BUTTON,"Cannot find Save button", 5);

    } else {
        waitForElementAndClick(SAVE_BUTTON,"Cannot find Save button", 5);}
    }

    public void goToTheHomeWikiPage() {
        waitForElementAndClick(GO_HOME_LINK, "Cannot find link 'go to the home page'", 5);
    }

    public void removeArticleFromSavedIfItAdded() {
        if(this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Cannot find and click remove from my list button", 5);
            this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find ADD_TO_MY_LIST_BUTTON", 10);
        }
    }
}


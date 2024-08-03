package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class SearchPageObject extends MainPageObject {

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }


    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /* TEMPLATES METHODS */


     protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT,
        SEARCH_EMPTY_PAGE_LOGO;

    @Step("Activate search")
    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input before clicking", 5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }
    @Step("Enter '{search_line}' into the search field")
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT,search_line, "Cannot find and type search input ", 5);
    }

    @Step("Waiting for search results")
    public void waitForSearchResult(String substring) {

        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring" + substring, 20);
    }

    @Step("Click on the article, finding it by the specified substring name")
    public void clickByArticleWithSubstring(String substring) {

        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring" + substring, 20);
    }

    @Step("Waiting for the search cancel button to appear")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    @Step("Waiting for the search cancel button to disappear")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }
    @Step("Click to exit search")
    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click cancel button", 5);
    }

    @Step("Get the number of articles found")
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find by the request",
                20
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Expecting empty search result to be displayed")
    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT, "Cannot find empty result element", 20);
    }
    @Step("Make sure the search results field is empty")
    public void assertThereIsNotResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "Cannot find any results");
    }
    @Step("Checking that multiple results were returned")
    public void assertSearchReturnedSeveralResults() {
        int amount_of_results = getAmountOfFoundArticles();
        Assert.assertTrue("The search returned less than two results", amount_of_results > 1);
    }

    @Step("Checking that multiple results were returned")
    public void assertSearchResultsBlockIsHidden() {
        this.assertElementPresent(SEARCH_EMPTY_PAGE_LOGO, "SEARCH_EMPTY_PAGE_LOGO is not found");
    }
}



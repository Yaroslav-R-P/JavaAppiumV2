package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Search tests")
public class SearchTests extends CoreTestCase {
    //тесты на проверку работы поиска
    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "WelcomePage")})
    @DisplayName("Checking search results")
    @Description("The test checks the search for correct execution and receipt of the required article")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch() {
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
            onboardingPageObject.skipOnboarding();
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "WelcomePage")})
    @DisplayName("Search cancellation check")
    @Description("Test checks for canceling search and hiding cancel button")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCancelSearch() {
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
            onboardingPageObject.skipOnboarding();
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "WelcomePage")})
    @DisplayName("Checking the number of results")
    @Description("The test checks for finding a valid value and producing a non-empty list of results")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testAmountOfNotEmptySearch() {
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
            onboardingPageObject.skipOnboarding();
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "WelcomePage")})
    @DisplayName("Checking invalid search")
    @Description("The test checks the search for an invalid value and the return of an empty search result")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch() {
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
            onboardingPageObject.skipOnboarding();
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_line = "sdfghjhn, v";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
    }

    //EX3
    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "WelcomePage")})
    @DisplayName("Checking to hide search results")
    @Description("The test checks the search for multiple search results and cancels the search to ensure that the result block is hidden")
    @Step("Starting test testCancelSearchResult")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearchResult() {

        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
            onboardingPageObject.skipOnboarding();
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.assertSearchReturnedSeveralResults();
        searchPageObject.clickCancelSearch();
        searchPageObject.assertSearchResultsBlockIsHidden();
    }
}

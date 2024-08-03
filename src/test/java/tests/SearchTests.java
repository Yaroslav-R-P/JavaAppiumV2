package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    //тесты на проверку работы поиска
    @Test
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
        //searchPageObject.assertThereIsNotResultOfSearch();
    }

    //EX3
    @Test
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

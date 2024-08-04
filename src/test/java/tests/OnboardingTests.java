package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.HomePageObject;
import lib.ui.WelcomePageObject;
import lib.ui.factories.HomePageObjectFactory;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

@Epic("Welcome page tests")
public class OnboardingTests extends CoreTestCase {

    //EX5
    @Test
    @Feature(value = "Welcome page")
    @DisplayName("Check the titles of the onboarding")
    @Description("The test passes onboarding by checking the titles of each of the screens")
    @Step("Starting test testCheckTheTitlesOfTheOnboarding")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckTheTitlesOfTheOnboarding() {
        WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);

        onboardingPageObject.checkTitleAndSwipe(1);
        onboardingPageObject.checkTitleAndSwipe(2);
        onboardingPageObject.checkTitleAndSwipe(3);
        onboardingPageObject.checkTitleAndSwipe(4);
        onboardingPageObject.clickGetStarted();
        HomePageObject homePageObject = HomePageObjectFactory.get(driver);
        homePageObject.isMainPageDisplayed();
    }
}

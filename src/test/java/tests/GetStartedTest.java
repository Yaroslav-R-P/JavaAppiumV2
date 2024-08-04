package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
//import lib.iOSTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

@Epic("Welcome page tests")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Feature(value = "Welcome page")
    @DisplayName("Walking through the welcome screen")
    @Description("The test is onboarded by clicking on the buttons to scroll through the screens")
    @Step("Starting test testPassThroughWelcome")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testPassThroughWelcome() {
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isMw()) {
            return;
        }

        WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);
        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForNewWayToExploreText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForAddOrEditPreferredLangText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForLearnMoreAboutDataCollectedText();
        welcomePageObject.clickGetStartedButtonFrom_iOS();
    }
}

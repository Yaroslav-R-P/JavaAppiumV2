package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase {
    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        //this.skipWelcomePageFromIOSApp();// если нужно скипать на iOS  онбординг
        this.openWikiWebPageForMobileWeb();
    }

    @After
    @Step("Remove driver and session")

    public void tearDown() {
        driver.quit();
    }

    @Step("Rotate screen to Portrait")
    protected void rotateScreenPortrait() {
        if(driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Rotate screen to Landscape")
    protected void rotateScreenLandscape() {
        if(driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Transition of the application to the background")
    protected void backgroundApp(long seconds) {
        if(driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("Method backgroundApp does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Method openWikiWebPageForMobileWeb(this method does nothing for Android and iOS)")
    protected void openWikiWebPageForMobileWeb() {
        if(Platform.getInstance().isMw()) {
            driver.get("https://en.m.wikipedia.org/wiki");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb does nothing for platform" + Platform.getInstance().getPlatformVar());

        }
    }

    private void skipWelcomePageFromIOSApp() {
        if (Platform.getInstance().isIOS()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);;
            welcomePageObject.clickSkip();
        }
    }

    private void createAllurePropertyFile() {
        String path = System.getProperty("allure.results.directory");
        try {
            Properties prors = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            prors.setProperty("Environment", Platform.getInstance().getPlatformVar());
            prors.store(fos,"See https://github.com/allure-framework/allure-app/wiki/Environment");
            fos.close();
        } catch (Exception e) {
            System.out.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}

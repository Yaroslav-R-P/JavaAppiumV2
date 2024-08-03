package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.WelcomePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for Articles") // совокупность фич касающихся одной большой темы
public class ArticleTests extends CoreTestCase {
    //тесты на статьи
    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")}) // отмечаем, какие фичи затронуты тестом
    @DisplayName("Compere article title with expected one") // отображаемое имя кейса в отчёте аллюра раздела Suites
    @Description("The test checks the presence and correct title text inside the opened article in web view") //описание того, как работает наш тест
    @Step("Starting test testCompereArticleTitle") //разметка шагов, описание последовательности действий
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompereArticleTitle() {
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
            onboardingPageObject.skipOnboarding();
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        String articleWithSubstring = "Java (programming language)";
        searchPageObject.clickByArticleWithSubstring(articleWithSubstring);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = articlePageObject.getArticleTitle();
      //  articlePageObject.takeScreenshot("article_page");
        Assert.assertEquals("We see unexpected title!", "Java (programming language)", articleTitle);
    }
    //EX6
    @Test
    public void testTitlePresence() {
        String articleNameWithSubstring = "Java (programming language)";

        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
            onboardingPageObject.skipOnboarding();
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(articleNameWithSubstring);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.assertTheArticleHasATitle(articleNameWithSubstring);

    }
}

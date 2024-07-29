package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.*;
import org.junit.Test;

public class MyListTests extends CoreTestCase {
    private static final String login = "Testovtest3344";
    private static final String password = "gjhhgkj!@hg83HH";

    //тесты списков статей
    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
            onboardingPageObject.skipOnboarding();
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        String articleWithSubstring = "Java (programming language)";
        searchPageObject.clickByArticleWithSubstring(articleWithSubstring);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);;
        articlePageObject.waitForTitleElement(articleWithSubstring);
        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyListAndGoToIt(name_of_folder);
            MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
            myListsPageObject.swipeByArticleToDelete(article_title); // андройд закончит тест тут удалив статью
        } if(Platform.getInstance().isIOS()) {
            articlePageObject.addArticlesToMySave(); // тут на вебе появляется логин форма
            articlePageObject.goToTheHomeWikiPage();

            NavigationUiPageObject navigationUiPageObject = NavigationUIFactory.get(driver);
            navigationUiPageObject.openMyLists();
            MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
            myListsPageObject.closeSynchronizationWindow();
            myListsPageObject.swipeAndTapDeleteArticle();
        } else {
            articlePageObject.addArticlesToMySave();

            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login,password);
            auth.submitForm();

            articlePageObject.waitForTitleElement(article_title);
            assertEquals("We are not on the same page after login",article_title, articlePageObject.getArticleTitle());

            NavigationUiPageObject navigation = NavigationUIFactory.get(driver);
            navigation.openNavigation();
            navigation.goToLists();

            MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
            Thread.sleep(10000);
            myListsPageObject.swipeByArticleToDelete(articleWithSubstring);

        }
    }
}

package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.*;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests of saved articles")
public class MyListTests extends CoreTestCase {
    private static final String login = "Testovtest3344";
    private static final String password = "gjhhgkj!@hg83HH";

    //тесты списков статей
    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article"),@Feature(value = "WelcomePage"),@Feature(value = "MyLists"), @Feature(value = "Authorization")})
    @DisplayName("Save the first article to your reading list")
    @Description("The test finds and adds an article to the reading list, then goes to the list and deletes it")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveFirstArticleToMyList() {
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
            Assert.assertEquals("We are not on the same page after login",article_title, articlePageObject.getArticleTitle());

            NavigationUiPageObject navigation = NavigationUIFactory.get(driver);
            navigation.openNavigation();
            navigation.goToLists();

            MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
            myListsPageObject.swipeByArticleToDelete(articleWithSubstring);

        }
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article"),@Feature(value = "WelcomePage"),@Feature(value = "MyLists"), @Feature(value = "Authorization")})
    @DisplayName("Saving or deleting an article to your reading list")
    @Description("The test finds and adds two articles to the reading list, removing the first one from the reading list")
    @Step("Starting test testSaveAndDeleteFirstArticle")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveAndDeleteFirstArticle() throws InterruptedException {
        //проверяем, что работает Android или iOS, скипаем онбординг
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
            onboardingPageObject.skipOnboarding();
        }
        //общие шаги  и переменные  для Android, iOS, Mobile_Web
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        String firstArticleWithSubstring = "Java (programming language)";
        String secondArticleWithSubstring = "Island in Indonesia";
        String secondArticleWithSubstringFromWeb = "Java";
        int amount_saved_articles = 0;
        searchPageObject.clickByArticleWithSubstring(firstArticleWithSubstring);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);;
        articlePageObject.waitForTitleElement(firstArticleWithSubstring);
        articlePageObject.addArticleToMyList();

        //после добавления первой статьи в сохранённые, делим логику на разные платформы

        //Для Android, iOS общий блок по добавлению доп. статьи и переход в сохранённые статьи
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            articlePageObject.setCloseArticleButton();
            searchPageObject.clickByArticleWithSubstring(secondArticleWithSubstring);
            articlePageObject.waitForTitleElement(secondArticleWithSubstring);
            articlePageObject.addArticleToMyList();
            articlePageObject.setCloseArticleButton();
            articlePageObject.cancelSearch();
            NavigationUiPageObject navigationUiPageObject = NavigationUIFactory.get(driver);
            navigationUiPageObject.openMyLists();
        }
        /*финальный шаг для каждой платформы раздельно ввиду особенностей работы приложений.
            в финале для iOS or Android мы получаем значение amount_saved_articles, которое потом
            ассертим с ожидаемым кол-вом статей.
         */
        if(Platform.getInstance().isIOS()) {
            MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
            myListsPageObject.closeSynchronizationWindow();
            myListsPageObject.swipeAndTapDeleteArticle();
            Thread.sleep(20000);
            amount_saved_articles = myListsPageObject.getAmountOfSavedArticles();
        }
        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
            myListsPageObject.openSavedArticles();
            Thread.sleep(20000);
            myListsPageObject.swipeByArticleToDelete(firstArticleWithSubstring);
            amount_saved_articles = myListsPageObject.getAmountOfSavedArticles();
        }

        /*
        Шаги теста для MW, после нажатия сохранения первой статьи. Включают в себя расширенные действия
        авторизации, навигации. Финалом является аналогичное мобилкам действие - получение amount_saved_articles
         */
        if(Platform.getInstance().isMw()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login,password);
            auth.submitForm();

            articlePageObject.goToTheHomeWikiPage();
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine("Java");
            Thread.sleep(10000);
            searchPageObject.clickByArticleWithSubstring(secondArticleWithSubstringFromWeb);
            articlePageObject.waitForTitleElement(secondArticleWithSubstringFromWeb);
            articlePageObject.addArticleToMyList();


            NavigationUiPageObject navigation = NavigationUIFactory.get(driver);
            navigation.openNavigation();
            navigation.goToLists();

            MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
            myListsPageObject.swipeByArticleToDelete(firstArticleWithSubstring);
            amount_saved_articles = myListsPageObject.getAmountOfSavedArticles();
        }


        Assert.assertEquals("Saved articles contain more than one result", 1, amount_saved_articles);
        System.out.println("Saved articles found " +amount_saved_articles);
    }
}

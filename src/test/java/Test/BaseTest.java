package Test;

import Base.AndroidConsts;
import Base.BaseClass;
import Base.DriverSingletonByJson;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BaseTest extends BaseClass {
    MainPage mainPage = new MainPage();
    LogInPage logPage = new LogInPage();
    AuthorizePage authPage = new AuthorizePage();
    HomePage homePage = new HomePage();
    ExplorePage explorePage = new ExplorePage();
    FirstPostPage firstPostPage = new FirstPostPage();
    SoftAssert softAssert = new SoftAssert();

    private void softAsserts() {
        softAssert.assertTrue(explorePage.CheckArePostsDisplayed(), "All posts are not displayed");
        softAssert.assertTrue(explorePage.CheckThePositionIsNot0(), "Result is null");
        softAssert.assertAll();
    }

    private void CommonSteps() {
        Assert.assertTrue(mainPage.CheckMainPageIsOpen());
        mainPage.ClickLogInButton();
        logPage.Authorize(AndroidConsts.appServer);
        authPage.ClickAuthorizeButton();
        Assert.assertTrue(homePage.CheckIsHomePageOpened(), "Home page is not opened");
        homePage.ClickExploreTab();
        Assert.assertTrue(explorePage.CheckArePostsOpened(), "Posts is not opened");
    }

    private void isKeyBoardVisible() {
        Assert.assertFalse(explorePage.isKeyboardVisible(), "Keyboard is visible");
    }

    @Test
    public void testCase1() {
        mainPage.CheckMainPageIsOpen();
        DriverSingletonByJson.getInstance().executeAdbCommand(AndroidConsts.closeApp);
        Assert.assertTrue(true, "App is closed and element is not displayed");
        DriverSingletonByJson.getInstance().executeAdbCommand(AndroidConsts.reOpenApp);
        CommonSteps();
        explorePage.ClickFirstPost();
        Assert.assertTrue(firstPostPage.CheckFirstPostIsOpen(), "First post is not opened");
        DriverSingletonByJson.getInstance().executeAdbCommand(AndroidConsts.closeApp);
    }

    @Test
    public void testCase2() {
        CommonSteps();
        softAsserts();
        explorePage.FillSearchField(AndroidConsts.baseText, AndroidConsts.text);
        Assert.assertTrue(explorePage.CheckDoesSearchFieldContains(AndroidConsts.text), "Search field is not contains" + AndroidConsts.text);
        explorePage.ClearSearchField(AndroidConsts.text);
        Assert.assertTrue(explorePage.CheckDoesSearchFieldContains(AndroidConsts.baseText), "Search field is not contains" + AndroidConsts.baseText);
    }

    @Test
    public void testCase3OpenAndCloseSearchField() {
        CommonSteps();
        explorePage.OpenAndCloseSearchField();
        isKeyBoardVisible();
    }

    @Test
    public void testCase3CloseBySendKeys() {
        CommonSteps();
        explorePage.TryCloseKeyBoardBySendKeys();
        isKeyBoardVisible();
    }

    @Test
    public void testCase3HideKeyboard() {
        CommonSteps();
        explorePage.hideKeyboard1();
        isKeyBoardVisible();
    }

    @Test
    public void testCase3EnterByKeyBoard() {
        CommonSteps();
        explorePage.EnterTextByKeyBoard();
        isKeyBoardVisible();
        Assert.assertEquals(explorePage.GetSearchFieldsText(AndroidConsts.filledByKeyboard), AndroidConsts.filledByKeyboard, "Not equals");
    }

    @Test
    public void testCase4(){
        CommonSteps();
        explorePage.findPost(4);
    }

    @Test
    public void testCase5() {
        CommonSteps();
        explorePage.getCurrentContext();
        explorePage.SwitchToWebView();
    }
}

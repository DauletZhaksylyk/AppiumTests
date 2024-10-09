package Test;

import Base.AndroidConsts;
import Base.BaseClass;
import DriverUtils.ExecuteAdbCommandUtil;
import DriverUtils.IsKeyBoardVisibleUtils;
import DriverUtils.SwitchToWebViewUtil;
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
        softAssert.assertTrue(explorePage.CheckThePositionIsNotNull(), "Result is null");
        softAssert.assertAll();
    }

    private void CommonSteps() {
        Assert.assertTrue(mainPage.CheckMainPageIsOpen());
        mainPage.ClickLogInButton();
        logPage.Authorize(AndroidConsts.APP_SERVER);
        authPage.ClickAuthorizeButton();
        Assert.assertTrue(homePage.CheckIsHomePageOpened(), "Home page is not opened");
        homePage.ClickExploreTab();
        Assert.assertTrue(explorePage.CheckArePostsOpened(), "Posts is not opened");
    }

    private void isKeyBoardVisible() {
        Assert.assertFalse(IsKeyBoardVisibleUtils.isKeyboardVisible(), "Keyboard is visible");
    }

    @Test
    public void testCase1() {
        mainPage.CheckMainPageIsOpen();
        ExecuteAdbCommandUtil.executeAdbCommand(AndroidConsts.CLOSE_APP);
        Assert.assertTrue(true, "App is closed and element is not displayed");
        ExecuteAdbCommandUtil.executeAdbCommand(AndroidConsts.RE_OPEN_APP);
        CommonSteps();
        explorePage.ClickFirstPost();
        Assert.assertTrue(firstPostPage.CheckFirstPostIsOpen(), "First post is not opened");
        ExecuteAdbCommandUtil.executeAdbCommand(AndroidConsts.CLOSE_APP);
    }

    @Test
    public void testCase2() {
        CommonSteps();
        softAsserts();
        explorePage.FillSearchField(AndroidConsts.BASE_TEXT, AndroidConsts.TEXT);
        Assert.assertTrue(explorePage.CheckDoesSearchFieldContains(AndroidConsts.TEXT), "Search field is not contains" + AndroidConsts.TEXT);
        explorePage.ClearSearchField(AndroidConsts.TEXT);
        Assert.assertTrue(explorePage.CheckDoesSearchFieldContains(AndroidConsts.BASE_TEXT), "Search field is not contains" + AndroidConsts.BASE_TEXT);
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
        Assert.assertEquals(explorePage.GetSearchFieldsText(AndroidConsts.FILLED_BY_KEYBOARD), AndroidConsts.FILLED_BY_KEYBOARD, "Not equals");
    }

    @Test
    public void testCase4(){
        CommonSteps();
        explorePage.verifyPostAtIndex(10);
    }

    @Test
    public void testCase5() {
        CommonSteps();
        explorePage.getCurrentContext();
        SwitchToWebViewUtil.SwitchToWebView();
    }
}

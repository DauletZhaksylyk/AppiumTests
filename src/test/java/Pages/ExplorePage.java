package Pages;

import Base.AndroidConsts;
import Base.BaseClass;
import Base.DriverSingletonByJson;
import DriverUtils.AndroidKeyUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class ExplorePage {
    private static final Logger LOGGER = LogManager.getLogger(ExplorePage.class);
    private static final By POSTS = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Posts\")");
    private static final By POST = new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"org.joinmastodon.android:id/time_and_username\")");
    private static final By ALL_POSTS = new By.ByClassName("android.widget.FrameLayout");
    private static final By SEARCH_FIELD = new By.ById("org.joinmastodon.android:id/search_text");
    private static final By GET_POST_BY_TAB = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\")");

    private static By SearchTextBox(String text) {
        return new AppiumBy.ByAndroidUIAutomator(String.format("new UiSelector().text(\"%s\")" , text));
    }

    public boolean CheckArePostsOpened() {
        BaseClass.getWait().until(ExpectedConditions.visibilityOfElementLocated(POSTS));
        return DriverSingletonByJson.getDriver().findElement(POSTS).isDisplayed();
    }


    public void ClickFirstPost() {
        BaseClass.getWait().until(ExpectedConditions.visibilityOfElementLocated(POST));
        DriverSingletonByJson.getDriver().findElement(POST).click();
    }

    public boolean CheckArePostsDisplayed() {
        return DriverSingletonByJson.getDriver().findElements(ALL_POSTS).stream().allMatch(WebElement::isDisplayed);
    }

    public boolean CheckThePositionIsNotNull() {
        Point elementLocation = DriverSingletonByJson.getDriver().findElement(SEARCH_FIELD).getLocation();
        int x = elementLocation.getX();
        int y = elementLocation.getY();
        return !(x == 0 && y == 0);
    }

    public String GetSearchFieldsText(String text) {
        return DriverSingletonByJson.getDriver().findElement(SearchTextBox(text)).getText();
    }

    public void FillSearchField(String baseText, String text) {
        DriverSingletonByJson.getDriver().findElement(SEARCH_FIELD).click();
        BaseClass.getWait().until(ExpectedConditions.visibilityOfElementLocated(SearchTextBox(baseText)));
        DriverSingletonByJson.getDriver().findElement(SearchTextBox(baseText)).sendKeys(text);
    }

    public boolean CheckDoesSearchFieldContains(String text) {
        return GetSearchFieldsText(text).equals(text);
    }

    public void ClearSearchField(String text) {
        DriverSingletonByJson.getDriver().findElement(SearchTextBox(text)).clear();
    }

    public void OpenAndCloseSearchField() {
        try {
            DriverSingletonByJson.getDriver().findElement(SEARCH_FIELD).click();
            DriverSingletonByJson.getDriver().findElement(SearchTextBox(AndroidConsts.BASE_TEXT)).click();
            AndroidKeyUtils.EnterAndroidKey(AndroidKey.SEARCH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void TryCloseKeyBoardBySendKeys() {
        DriverSingletonByJson.getDriver().findElement(SEARCH_FIELD).click();
        DriverSingletonByJson.getDriver().findElement(SearchTextBox(AndroidConsts.BASE_TEXT)).sendKeys("");
    }

    public void EnterTextByKeyBoard() {
        DriverSingletonByJson.getDriver().findElement(SearchTextBox(AndroidConsts.BASE_TEXT)).click();
        AndroidKeyUtils.EnterAndroidKey(AndroidKey.A);
        AndroidKeyUtils.EnterAndroidKey(AndroidKey.B);
        AndroidKeyUtils.EnterAndroidKey(AndroidKey.C);
        AndroidKeyUtils.EnterAndroidKey(AndroidKey.SEARCH);
    }

    public void hideKeyboard1() {
        DriverSingletonByJson.getDriver().hideKeyboard();
    }

    public void getCurrentContext() {
        DriverSingletonByJson.getDriver().getContext();
    }

    public void verifyPostAtIndex(int targetIndex) {
        int visiblePostsCount = 0;

        while (visiblePostsCount < targetIndex) {
            BaseClass.getWait().until(ExpectedConditions.visibilityOfElementLocated(GET_POST_BY_TAB));

            try {
                List<WebElement> foundPosts = DriverSingletonByJson.getDriver().findElements(GET_POST_BY_TAB);

                for (WebElement post : foundPosts) {
                    if (post.isDisplayed()) {
                        visiblePostsCount += 1;

                        if (visiblePostsCount == targetIndex) {
                            LOGGER.debug("Пост на индексе " + targetIndex + " найден.");
                            return;
                        }
                    }
                }
            } catch (NoSuchElementException e) {
                LOGGER.debug("Posts not found, swiping down.");
            } catch (StaleElementReferenceException e) {
                LOGGER.debug("Stale element reference, trying again.");
            }

            swipeDown();
        }

        throw new IndexOutOfBoundsException("Пост на индексе " + targetIndex + " не найден.");
    }


    private void swipeDown() {
        int width = DriverSingletonByJson.getDriver().manage().window().getSize().getWidth();
        int height = DriverSingletonByJson.getDriver().manage().window().getSize().getHeight();

        int startX = width / 2;
        int startY = (int) (height * 0.85);
        int endY = (int) (height * 0.1);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        DriverSingletonByJson.getDriver().perform(Arrays.asList(swipe));
        LOGGER.debug("Свайп выполнен: startX=" + startX + ", startY=" + startY + ", endY=" + endY);
    }
}

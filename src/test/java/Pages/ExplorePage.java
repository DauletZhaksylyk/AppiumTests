package Pages;

import Base.AndroidConsts;
import Base.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.internal.shadowed.jackson.databind.ser.Serializers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class ExplorePage {
    private static final Logger logger = LogManager.getLogger(ExplorePage.class);
    private static final By Posts = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Posts\")");
    private static final By Post = new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"org.joinmastodon.android:id/time_and_username\")");
    private static final By AllPosts = new By.ByClassName("android.widget.FrameLayout");
    private static final By SearchField = new By.ById("org.joinmastodon.android:id/search_text");
    private static final By accCover = new By.ById("org.joinmastodon.android:id/name");//org.joinmastodon.android:id/name
    private static final By postForm = new By.ByClassName("android.widget.RelativeLayout");//android.widget.RelativeLayout
    private static final By GetAllPosts = new By.ByXPath("//android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]");
    private static final By getListByNames = new By.ByXPath("//android.widget.RelativeLayout/android.widget.TextView");

    private static By SearchTextBox(String text) {
        return new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"" + text + "\")");
    }

    public boolean CheckArePostsOpened() {
        BaseClass.wait1().until(ExpectedConditions.visibilityOfElementLocated(Posts));
        return BaseClass.getDriver().findElement(Posts).isDisplayed();
    }


    public void ClickFirstPost() {
        BaseClass.wait1().until(ExpectedConditions.visibilityOfElementLocated(Post));
        BaseClass.getDriver().findElement(Post).click();
    }

    public boolean CheckArePostsDisplayed() {
        return BaseClass.getDriver().findElements(AllPosts).stream().allMatch(WebElement::isDisplayed);
    }

    public boolean CheckThePositionIsNot0() {
        Point elementLocation = BaseClass.getDriver().findElement(SearchField).getLocation();
        int x = elementLocation.getX();
        int y = elementLocation.getY();
        return !(x == 0 && y == 0);
    }

    public String GetSearchFieldsText(String text) {
        return BaseClass.getDriver().findElement(SearchTextBox(text)).getText();
    }

    public void FillSearchField(String baseText, String text) {
        BaseClass.getDriver().findElement(SearchField).click();
        BaseClass.wait1().until(ExpectedConditions.visibilityOfElementLocated(SearchTextBox(baseText)));
        BaseClass.getDriver().findElement(SearchTextBox(baseText)).sendKeys(text);
    }

    public boolean CheckDoesSearchFieldContains(String text) {
        return GetSearchFieldsText(text).equals(text);
    }

    public void ClearSearchField(String text) {
        BaseClass.getDriver().findElement(SearchTextBox(text)).clear();
    }

    public void OpenAndCloseSearchField() {
        try {
            BaseClass.getDriver().findElement(SearchField).click();
            BaseClass.getDriver().findElement(SearchTextBox(AndroidConsts.baseText)).click();
            EnterAndroidKey(AndroidKey.SEARCH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isKeyboardVisible() {
        Object isKeyboardShown = BaseClass.getDriver().getCapabilities().getCapability("isKeyboardShown");
        return isKeyboardShown != null && isKeyboardShown.toString().equalsIgnoreCase("true");
    }

    public void TryCloseKeyBoardBySendKeys() {
        BaseClass.getDriver().findElement(SearchField).click();
        BaseClass.getDriver().findElement(SearchTextBox(AndroidConsts.baseText)).sendKeys("");
    }

    public void EnterTextByKeyBoard() {
        BaseClass.getDriver().findElement(SearchTextBox(AndroidConsts.baseText)).click();
        EnterAndroidKey(AndroidKey.A);
        EnterAndroidKey(AndroidKey.B);
        EnterAndroidKey(AndroidKey.C);
        EnterAndroidKey(AndroidKey.SEARCH);
    }

    public void EnterAndroidKey(AndroidKey key) {
        BaseClass.getDriver().pressKey(new KeyEvent(key));
    }

    public void hideKeyboard1() {
        BaseClass.getDriver().hideKeyboard();
    }

    public void getCurrentContext() {
        BaseClass.getDriver().getContext();
    }

    public void SwitchToWebView() {
        Set<String> contextNames = BaseClass.getDriver().getContextHandles();
        contextNames.forEach(contextName -> {
            if (contextName.contains("WEBVIEW")) {
                BaseClass.getDriver().context(contextName);
            }
        });
    }


    public WebElement findPost(int targetIndex) {
        List<WebElement> posts;
        int maxAttempts = 10; // Максимальное количество попыток
        int attempts = 0;

        while (attempts < maxAttempts) {
            // Ждём, пока элементы станут видимыми
            BaseClass.wait1().until(ExpectedConditions.visibilityOfElementLocated(getListByNames));
            posts = BaseClass.getDriver().findElements(getListByNames);
            logger.debug("Количество найденных постов: " + posts.size());

            // Проверяем, не превышает ли количество найденных постов целевой индекс
            if (posts.size() > targetIndex) {
                WebElement targetPost = posts.get(targetIndex);
                BaseClass.wait1().until(ExpectedConditions.visibilityOf(targetPost));
                return targetPost; // Если элемент найден, возвращаем его
            }

            // Если целевой индекс не найден, выполняем свайп
            swipeDown();

            // Ожидание, чтобы удостовериться, что новые посты загружены
            BaseClass.wait1().until(ExpectedConditions.numberOfElementsToBeMoreThan(getListByNames, posts.size()));

            attempts++;
        }

        logger.warn("Пост с индексом " + targetIndex + " не найден после " + attempts + " попыток.");
        return null; // Если пост не найден после всех попыток
        }


    private void swipeDown() {
        int width = BaseClass.getDriver().manage().window().getSize().getWidth();
        int height = BaseClass.getDriver().manage().window().getSize().getHeight();

        // Начальные и конечные позиции для свайпа вниз
        int startX = width / 2; // Центр экрана по X
        int startY = (int) (height * 0.7); // Начальная позиция по Y
        int endY = (int) (height * 0.1); // Конечная позиция по Y

        // Создаем объект PointerInput для имитации касания
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(10000), PointerInput.Origin.viewport(), startX, endY)) // Увеличиваем время выполнения до 5 секунд
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Выполняем сваййп
        BaseClass.getDriver().perform(Arrays.asList(swipe));
        logger.debug("Свайп выполнен: startX=" + startX + ", startY=" + startY + ", endY=" + endY);
    }
}

package Pages;

import Base.BaseClass;
import Base.DriverSingletonByJson;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {
    private static final By HOME_PAGE = new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Home\").instance(0)");
    private static final By EXPLORE_TAB = new By.ById("org.joinmastodon.android:id/tab_search");

    public boolean CheckIsHomePageOpened() {
        BaseClass.getWait().until(ExpectedConditions.visibilityOfElementLocated(HOME_PAGE));
        return DriverSingletonByJson.getDriver().findElement(HOME_PAGE).isDisplayed();
    }

    public void ClickExploreTab() {
        DriverSingletonByJson.getDriver().findElement(EXPLORE_TAB).click();
    }

}

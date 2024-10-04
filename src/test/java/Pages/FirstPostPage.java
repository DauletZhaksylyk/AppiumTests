package Pages;

import Base.BaseClass;
import Base.DriverSingletonByJson;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FirstPostPage {
    private static final By FIRST_POST = new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"org.joinmastodon.android:id/text\")");

    public boolean CheckFirstPostIsOpen() {
        BaseClass.getWait().until(ExpectedConditions.visibilityOfElementLocated(FIRST_POST));
        return DriverSingletonByJson.getDriver().findElement(FIRST_POST).isDisplayed();
    }
}

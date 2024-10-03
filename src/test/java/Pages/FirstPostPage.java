package Pages;

import Base.BaseClass;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FirstPostPage {
    private static final By FirstPost = new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"org.joinmastodon.android:id/text\")");

    public boolean CheckFirstPostIsOpen() {
        BaseClass.wait1().until(ExpectedConditions.visibilityOfElementLocated(FirstPost));
        return BaseClass.getDriver().findElement(FirstPost).isDisplayed();
    }
}

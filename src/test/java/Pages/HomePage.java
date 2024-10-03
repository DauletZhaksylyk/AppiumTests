package Pages;

import Base.BaseClass;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {
    private static final By HomePage = new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Home\").instance(0)");
    private static final By ExploreTab = new By.ById("org.joinmastodon.android:id/tab_search");

    public boolean CheckIsHomePageOpened() {
        BaseClass.wait1().until(ExpectedConditions.visibilityOfElementLocated(HomePage));
        return BaseClass.getDriver().findElement(HomePage).isDisplayed();
    }

    public void ClickExploreTab() {
        BaseClass.getDriver().findElement(ExploreTab).click();
    }

}

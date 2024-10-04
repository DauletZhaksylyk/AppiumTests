package Pages;

import Base.BaseClass;
import Base.DriverSingletonByJson;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthorizePage {
    private static final By AUTHORIZE_BUTTON = new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Authorize\")");
    private static final By DENY_BUTTON = new By.ById("com.android.permissioncontroller:id/permission_deny_button");

    public void ClickAuthorizeButton() {
        BaseClass.getWait().until(ExpectedConditions.visibilityOfElementLocated(AUTHORIZE_BUTTON));
        DriverSingletonByJson.getDriver().findElement(AUTHORIZE_BUTTON).click();
        BaseClass.getWait().until(ExpectedConditions.visibilityOfElementLocated(DENY_BUTTON));
        DriverSingletonByJson.getDriver().findElement(DENY_BUTTON).click();
    }
}

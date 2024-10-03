package Pages;

import Base.BaseClass;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthorizePage {
    private static final By AuthorizeButton = new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Authorize\")");
    private static final By DenyButton = new By.ById("com.android.permissioncontroller:id/permission_deny_button");

    public void ClickAuthorizeButton() {
        BaseClass.wait1().until(ExpectedConditions.visibilityOfElementLocated(AuthorizeButton));
        BaseClass.getDriver().findElement(AuthorizeButton).click();
        BaseClass.wait1().until(ExpectedConditions.visibilityOfElementLocated(DenyButton));
        BaseClass.getDriver().findElement(DenyButton).click();
    }
}

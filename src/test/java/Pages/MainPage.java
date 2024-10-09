package Pages;

import Base.BaseClass;
import Base.DriverSingletonByJson;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage {
    private static final By BUTTON_ID = new By.ById("org.joinmastodon.android:id/btn_log_in");

    public boolean CheckMainPageIsOpen() {
        BaseClass.getWait().until(ExpectedConditions.visibilityOfElementLocated(BUTTON_ID));
        return DriverSingletonByJson.getDriver().findElement(BUTTON_ID).isDisplayed();
    }

    public void ClickLogInButton() {
        DriverSingletonByJson.getDriver().findElement(BUTTON_ID).click();
    }
}

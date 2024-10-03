package Pages;

import Base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage {
    private static final By ButtonId = new By.ById("org.joinmastodon.android:id/btn_log_in");

    public boolean CheckMainPageIsOpen() {
        BaseClass.wait1().until(ExpectedConditions.visibilityOfElementLocated(ButtonId));
        return BaseClass.getDriver().findElement(ButtonId).isDisplayed();
    }

    public void ClickLogInButton() {
        BaseClass.getDriver().findElement(ButtonId).click();
    }
}

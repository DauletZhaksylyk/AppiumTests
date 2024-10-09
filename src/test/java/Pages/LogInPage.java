package Pages;

import Base.BaseClass;
import Base.DriverSingletonByJson;
import org.openqa.selenium.By;

public class LogInPage {
    private static final By SEARCH_FIELD = new By.ById("org.joinmastodon.android:id/search_edit");
    private static final By CHECKBOX = new By.ById("org.joinmastodon.android:id/radiobtn");
    private static final By NEXT_BUTTON = new By.ById("org.joinmastodon.android:id/btn_next");

    public void FillSearchField(String server) {
        DriverSingletonByJson.getDriver().findElement(SEARCH_FIELD).sendKeys(server);
    }

    public void ClickCheckBox() {
        DriverSingletonByJson.getDriver().findElement(CHECKBOX).click();
    }

    public void ClickNextButton() {
        DriverSingletonByJson.getDriver().findElement(NEXT_BUTTON).click();
    }

    public void Authorize(String server) {
        FillSearchField(server);
        ClickCheckBox();
        ClickNextButton();
    }
}

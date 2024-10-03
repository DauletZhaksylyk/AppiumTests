package Pages;

import Base.BaseClass;
import org.openqa.selenium.By;

public class LogInPage {
    private static final By SearchField = new By.ById("org.joinmastodon.android:id/search_edit");
    private static final By Checkbox = new By.ById("org.joinmastodon.android:id/radiobtn");
    private static final By NextButton = new By.ById("org.joinmastodon.android:id/btn_next");

    public void FillSearchField(String server) {
        BaseClass.getDriver().findElement(SearchField).sendKeys(server);
    }

    public void ClickCheckBox() {
        BaseClass.getDriver().findElement(Checkbox).click();
    }

    public void ClickNextButton() {
        BaseClass.getDriver().findElement(NextButton).click();
    }

    public void Authorize(String server) {
        FillSearchField(server);
        ClickCheckBox();
        ClickNextButton();
    }
}

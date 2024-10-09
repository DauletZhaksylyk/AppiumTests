package Base;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;


public class BaseClass {
    protected static WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        DriverSingletonByJson.getDriver();
        getWait();
    }

    @AfterMethod
    public static void quitDriver() {
        DriverSingletonByJson.getDriver().quit();
    }

    public static WebDriverWait getWait() {
        return wait = new WebDriverWait(DriverSingletonByJson.getDriver(), Duration.ofSeconds(15));
    }

}

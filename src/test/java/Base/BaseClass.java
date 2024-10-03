package Base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseClass {
    protected static AndroidDriver driver;
    protected static WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        getDriver();
        wait1();
    }

    @AfterMethod
    public static void quitDriver() {
        driver.quit();
    }

    public static AndroidDriver getDriver() {
        return driver = DriverSingletonByJson.getDriver();
    }

    public static WebDriverWait wait1() {
        return wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
}

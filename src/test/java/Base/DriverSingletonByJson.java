package Base;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class DriverSingletonByJson {
    private static DriverSingletonByJson instance;
    private static AndroidDriver driver;

    private DriverSingletonByJson() {
        DesiredCapabilities capabilities = loadCapabilitiesFromJson(AndroidConsts.CAPABILITY_PATH);
        try {
            driver = new AndroidDriver(new URL(AndroidConsts.URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static DriverSingletonByJson getInstance() {
        if (instance == null){
            instance = new DriverSingletonByJson();
        }
        return instance;
    }

    private static DesiredCapabilities loadCapabilitiesFromJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        try {
            Map<String, Map<String, String>> jsonMap = mapper.readValue(new File(filePath), Map.class);
            Map<String, String> caps = jsonMap.get("capabilities");
            for (Map.Entry<String, String> entry : caps.entrySet()) {
                capabilities.setCapability(entry.getKey(), entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return capabilities;
    }

    public static AndroidDriver getDriver() {
        if (driver == null) {
            getInstance();
        }
        return driver;
    }
}

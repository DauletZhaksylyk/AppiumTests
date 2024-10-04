package DriverUtils;

import Base.BaseClass;
import Base.DriverSingletonByJson;

public class IsKeyBoardVisibleUtils {
    public static boolean isKeyboardVisible() {
        Object isKeyboardShown = DriverSingletonByJson.getDriver().getCapabilities().getCapability("isKeyboardShown");
        return isKeyboardShown != null && isKeyboardShown.toString().equalsIgnoreCase("true");
    }
}

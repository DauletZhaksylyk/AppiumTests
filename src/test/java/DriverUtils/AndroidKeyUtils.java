package DriverUtils;

import Base.BaseClass;
import Base.DriverSingletonByJson;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidKeyUtils {
    public static void EnterAndroidKey(AndroidKey key) {
        DriverSingletonByJson.getDriver().pressKey(new KeyEvent(key));
    }
}

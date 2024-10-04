package DriverUtils;

import Base.BaseClass;
import Base.DriverSingletonByJson;

import java.util.Set;

public class SwitchToWebViewUtil {
    public static void SwitchToWebView() {
        Set<String> contextNames = DriverSingletonByJson.getDriver().getContextHandles();
        contextNames.forEach(contextName -> {
            if (contextName.contains("WEBVIEW")) {
                DriverSingletonByJson.getDriver().context(contextName);
            }
        });
    }
}

package Base;

public class AndroidConsts {
    public static final String url = "http://127.0.0.1:4723/";
    public static final String appPackage = "org.joinmastodon.android";
    public static final String closeApp = "am force-stop " + appPackage;
    public static final String reOpenApp = "monkey -p " + appPackage + " -c android.intent.category.LAUNCHER 1";
    public static final String appServer = "mastodon.social";
    public static final String text = "tests";
    public static final String baseText = "Search Mastodon";
    public static final String filledByKeyboard = "abc";
    public static final String capabilityPath = "src/test/resources/capabalititesjson.json";
    public static final String Id = "4";

}

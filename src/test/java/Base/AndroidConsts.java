package Base;

public class AndroidConsts {
    public static final String URL = "http://127.0.0.1:4723/";
    public static final String APP_PACKAGE = "org.joinmastodon.android";
    public static final String CLOSE_APP = "am force-stop " + APP_PACKAGE;
    public static final String RE_OPEN_APP = "monkey -p " + APP_PACKAGE + " -c android.intent.category.LAUNCHER 1";
    public static final String APP_SERVER = "mastodon.social";
    public static final String TEXT = "tests";
    public static final String BASE_TEXT = "Search Mastodon";
    public static final String FILLED_BY_KEYBOARD = "abc";
    public static final String CAPABILITY_PATH = "src/test/resources/capabalititesjson.json";
    public static final int ID = 4;

}

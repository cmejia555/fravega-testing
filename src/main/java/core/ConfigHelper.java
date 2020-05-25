package core;

public class ConfigHelper {

    public static final String BROWSER_NAME = "browserName";
    public static final String APP_URL = "appUrl";
    public static final String APP_DEFAULT_WAIT = "appDefaultWait";
    public static final String PAGE_LOAD_WAIT = "pageLoadWait";
    public static final String DRIVER_PATH = "driverPath";

    public static String getBrowserName() {
        return System.getProperty(BROWSER_NAME);
    }

    public static String getAppUrl() {
        return System.getProperty(APP_URL);
    }

    public static int getAppDefaultWait() {
        return Integer.parseInt(System.getProperty(APP_DEFAULT_WAIT));
    }

    public static int getPageLoadTimeOut() {
        return Integer.parseInt(System.getProperty(PAGE_LOAD_WAIT));
    }

    public static String getDriverPath() {
        return System.getProperty(DRIVER_PATH);
    }
}

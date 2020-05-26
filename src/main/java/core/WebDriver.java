package core;

import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


public class WebDriver {
    private static org.openqa.selenium.WebDriver driver;
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    public static org.openqa.selenium.WebDriver getInstance() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }


    public static org.openqa.selenium.WebDriver createDriver() {
        switch (ConfigHelper.getBrowserName()) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", ConfigHelper.getDriverPath());
                driver = new ChromeDriver(Capabilities.getDefaultChromeDesiredCapabilities());
                break;
            default:
                throw new IllegalArgumentException("Browser is not supported");
        }

        driver.manage().timeouts().pageLoadTimeout(ConfigHelper.getPageLoadTimeOut(), TimeUnit.SECONDS);
        driver.get(ConfigHelper.getAppUrl());
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }
}

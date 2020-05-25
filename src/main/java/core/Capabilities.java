package core;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilities {

    public static DesiredCapabilities getDefaultChromeDesiredCapabilities() {
        DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=es");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return chromeCapabilities;
    }
}

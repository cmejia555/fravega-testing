package pages;

import core.ConfigHelper;
import core.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected static org.openqa.selenium.WebDriver driver;
    protected static WebDriverWait driverWait;

    protected BasePage() {
        driver = WebDriver.getInstance();
        driverWait = new WebDriverWait(driver, ConfigHelper.getAppDefaultWait());
    }

    protected boolean exists(By locator) {
        boolean status = false;

        try {
            status = driverWait.until(ExpectedConditions.and(
                    ExpectedConditions.presenceOfElementLocated(locator),
                    ExpectedConditions.visibilityOfElementLocated(locator)));
        } catch (NoSuchElementException | TimeoutException e) {
            e.printStackTrace();
        }

        return status;
    }

    protected WebElement get(By locator) {
        int tries = 5;
        while (tries > 0) {
            try {
                return driverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (StaleElementReferenceException e ) {
                e.printStackTrace();
            }
            tries--;
        }

        return null;
    }

    protected void sendKeys(By locator, String value) {
        get(locator).sendKeys(value);
    }

    protected void click(By locator) {
        get(locator).click();
    }

    protected String getText(By locator) {
        return get(locator).getText();
    }
}

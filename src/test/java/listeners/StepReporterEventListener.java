package listeners;

import core.WebDriver;
import io.qameta.allure.Attachment;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.json.JsonException;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StepReporterEventListener implements StepLifecycleListener {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(StepReporterEventListener.class));

    @Override
    public void beforeStepStart(StepResult result) {
        LOGGER.info("Step to be Completed: " + result.getName());
    }

    @Override
    public void afterStepUpdate(StepResult result) {
        LOGGER.info("Step Completed: " + result.getName());
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            LOGGER.log(Level.INFO, "Get page source after failed or broken step");
            savePageSource();
        }
        this.takeScreenShot();
        this.saveLog();
    }

    @Attachment("Step screenshot")
    public byte[] takeScreenShot() {
        byte[] screenShot = new byte[0];
        try {
            screenShot = ((TakesScreenshot) WebDriver.getInstance()).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            LOGGER.log(Level.WARNING, "\u26A0: Error while taking screenshot");
            e.printStackTrace();
        }
        return screenShot;
    }

    @Attachment(value = "pagesource.txt", type = "text/xml")
    private String savePageSource() {
        String pagesource = null;
        try {
            pagesource = WebDriver.getInstance().getPageSource();
        } catch (WebDriverException e) {
            LOGGER.log(Level.WARNING, "\u26A0: Error while getting pagesource");
            e.printStackTrace();
        }
        return pagesource;
    }

    @Attachment(
            value = "browser.log",
            type = "text/plain"
    )
    public byte[] saveLog() {
        List<LogEntry> logEntries;
        try {
            logEntries = WebDriver.getInstance().manage().logs().get(LogType.BROWSER).getAll();
        } catch (JsonException e) {
            String message = "WARNING: Network log is not available";
            LOGGER.warning(message);
            return message.getBytes();
        }

        return this.saveLog(logEntries);
    }

    public byte[] saveLog(List<LogEntry> logEntries) {
        ByteArrayOutputStream logOutputStream = new ByteArrayOutputStream();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        logEntries.forEach((entry) -> {
            try {
                logOutputStream.write(String.format("[%s] %s: %s \n", entry.getLevel().getName(), simpleDateFormat.format(entry.getTimestamp()), entry.getMessage()).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        return logOutputStream.toByteArray();
    }

}
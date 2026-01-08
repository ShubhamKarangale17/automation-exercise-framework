package main.java.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import config.ConfigReader;

/**
 * ScreenshotUtils - Handles screenshot capture functionality
 */
public class ScreenshotUtils {

    /**
     * Capture screenshot and save it with timestamp
     *
     * @param driver - WebDriver instance
     * @param testName - Name of the test
     * @return Path of the saved screenshot
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String screenshotPath = ConfigReader.getScreenshotPath() + testName + "_" + timestamp + ".png";

            // Create directory if it doesn't exist
            File screenshotDir = new File(ConfigReader.getScreenshotPath());
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            FileUtils.copyFile(srcFile, new File(screenshotPath));
            return screenshotPath;
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}

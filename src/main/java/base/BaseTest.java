package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;
import utils.ReportManager;
import utils.ScreenshotUtils;

/**
 * BaseTest - Base class for all test classes
 * Provides common setup and teardown methods
 */
public class BaseTest {
    protected WebDriver driver;

    /**
     * Setup - Executes before each test method
     * Initializes WebDriver and test report
     */
    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initializeDriver();
        ReportManager.startTest(this.getClass().getSimpleName(), 
                               "Test for " + this.getClass().getSimpleName());
    }

    /**
     * Teardown - Executes after each test method
     * Quits WebDriver and flushes report
     */
    @AfterMethod
    public void tearDown() {
        try {
            if (driver != null) {
                ScreenshotUtils.captureScreenshot(driver, "AfterTest");
                DriverFactory.quitDriver();
            }
        } catch (Exception e) {
            System.err.println("Error during teardown: " + e.getMessage());
        }
        ReportManager.flushReport();
    }
}

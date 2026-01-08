package main.java.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import config.ConfigReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ReportManager - Manages Extent Reports for test execution
 */
public class ReportManager {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    /**
     * Initialize Extent Reports
     */
    public static void initializeReport() {
        String reportPath = ConfigReader.getReportPath() + "extent-report-" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setReportName("Automation Exercise Framework - Test Report");
        sparkReporter.config().setDocumentTitle("Test Execution Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Browser", ConfigReader.getBrowser());
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
    }

    /**
     * Create a test in the report
     *
     * @param testName - Name of the test
     * @param description - Test description
     */
    public static void startTest(String testName, String description) {
        if (extentReports == null) {
            initializeReport();
        }
        extentTest = extentReports.createTest(testName, description);
    }

    /**
     * Log pass event
     *
     * @param message - Message to log
     */
    public static void logPass(String message) {
        if (extentTest != null) {
            extentTest.pass(message);
        }
    }

    /**
     * Log fail event
     *
     * @param message - Message to log
     */
    public static void logFail(String message) {
        if (extentTest != null) {
            extentTest.fail(message);
        }
    }

    /**
     * Log skip event
     *
     * @param message - Message to log
     */
    public static void logSkip(String message) {
        if (extentTest != null) {
            extentTest.skip(message);
        }
    }

    /**
     * Log info event
     *
     * @param message - Message to log
     */
    public static void logInfo(String message) {
        if (extentTest != null) {
            extentTest.info(message);
        }
    }

    /**
     * Attach screenshot to the report
     *
     * @param screenshotPath - Path to screenshot
     */
    public static void attachScreenshot(String screenshotPath) {
        if (extentTest != null && screenshotPath != null) {
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
    }

    /**
     * Flush the report
     */
    public static void flushReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}

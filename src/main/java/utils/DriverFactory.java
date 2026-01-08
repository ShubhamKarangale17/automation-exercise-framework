package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * DriverFactory - Manages WebDriver instance creation and management
 * Provides centralized driver management for Selenium tests
 */
public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browser configuration
     *
     * @return WebDriver instance
     */
    public static WebDriver initializeDriver() {
        String browserType = ConfigReader.getBrowser().toLowerCase();

        if (browserType.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            // Add headless mode if configured
            if (ConfigReader.isHeadless()) {
                options.addArguments("--headless");
            }

            // Add additional options for better performance
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=" + ConfigReader.getProperty("window_size"));

            WebDriver chromeDriver = new ChromeDriver(options);
            driver.set(chromeDriver);
        } else {
            throw new IllegalArgumentException("Browser '" + browserType + "' is not supported. Use 'chrome'.");
        }

        // Set timeouts
        getDriver().manage().timeouts()
                .implicitlyWait(java.time.Duration.ofSeconds(ConfigReader.getImplicitWait()))
                .pageLoadTimeout(java.time.Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));

        return getDriver();
    }

    /**
     * Get current WebDriver instance
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quit WebDriver and remove from ThreadLocal
     */
    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}

package main.java.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader - Reads configuration from properties file
 * Provides centralized access to all configuration properties
 */
public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            String configPath = "src/test/resources/config.properties";
            FileInputStream fis = new FileInputStream(configPath);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.err.println("Error loading config.properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Get property value by key
     *
     * @param key - Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get browser type
     */
    public static String getBrowser() {
        return getProperty("browser");
    }

    /**
     * Get base URL for UI application
     */
    public static String getBaseUrl() {
        return getProperty("base_url");
    }

    /**
     * Get API base URL
     */
    public static String getApiBaseUrl() {
        return getProperty("api_base_url");
    }

    /**
     * Get implicit wait timeout in seconds
     */
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit_wait"));
    }

    /**
     * Get explicit wait timeout in seconds
     */
    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit_wait"));
    }

    /**
     * Get page load timeout in seconds
     */
    public static int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page_load_timeout"));
    }

    /**
     * Check if headless mode is enabled
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    /**
     * Get report path
     */
    public static String getReportPath() {
        return getProperty("report_path");
    }

    /**
     * Get screenshot path
     */
    public static String getScreenshotPath() {
        return getProperty("screenshot_path");
    }

    /**
     * Get API request timeout in milliseconds
     */
    public static int getApiTimeout() {
        return Integer.parseInt(getProperty("api_request_timeout"));
    }

    /**
     * Get valid email for login
     */
    public static String getValidEmail() {
        return getProperty("valid_email");
    }

    /**
     * Get valid password for login
     */
    public static String getValidPassword() {
        return getProperty("valid_password");
    }

    /**
     * Get invalid email for negative testing
     */
    public static String getInvalidEmail() {
        return getProperty("invalid_email");
    }

    /**
     * Get invalid password for negative testing
     */
    public static String getInvalidPassword() {
        return getProperty("invalid_password");
    }
}

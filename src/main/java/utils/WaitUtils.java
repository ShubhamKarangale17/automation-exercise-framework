package main.java.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import config.ConfigReader;

/**
 * WaitUtils - Provides explicit wait utilities
 * Implements waits for various element states
 */
public class WaitUtils {
    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    /**
     * Wait for element to be visible
     *
     * @param locator - By locator of the element
     * @return WebElement once it is visible
     */
    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     *
     * @param locator - By locator of the element
     * @return WebElement once it is clickable
     */
    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to be present in DOM
     *
     * @param locator - By locator of the element
     * @return WebElement once it is present
     */
    public WebElement waitForElementToBePresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for element to disappear
     *
     * @param locator - By locator of the element
     * @return true if element disappears
     */
    public boolean waitForElementToDisappear(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for text to be present in element
     *
     * @param locator - By locator of the element
     * @param text - Expected text
     * @return true if text is present
     */
    public boolean waitForTextInElement(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Wait for page title
     *
     * @param title - Expected page title
     * @return true if title matches
     */
    public boolean waitForPageTitle(String title) {
        return wait.until(ExpectedConditions.titleIs(title));
    }

    /**
     * Wait for URL to contain
     *
     * @param urlPortion - URL portion to expect
     * @return true if URL contains the portion
     */
    public boolean waitForUrlContains(String urlPortion) {
        return wait.until(ExpectedConditions.urlContains(urlPortion));
    }

    /**
     * Generic wait method with custom duration
     *
     * @param duration - Wait duration in seconds
     */
    public void hardWait(int duration) {
        try {
            Thread.sleep(duration * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

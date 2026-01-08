package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

/**
 * HomePage - Page Object for Home Page
 * Contains all elements and methods related to home page
 */
public class HomePage {
    private WebDriver driver;
    private WaitUtils waitUtils;

    // Locators
    private By homePageHeading = By.xpath("//h2[contains(text(), 'Features')]");
    private By loginLink = By.xpath("//a[contains(text(), 'Signup / Login')]");
    private By productsLink = By.xpath("//a[contains(text(), 'Products')]");
    private By cartLink = By.xpath("//a[contains(text(), 'Cart')]");
    private By logoutLink = By.xpath("//a[contains(text(), 'Logout')]");
    private By homeLink = By.xpath("//a[contains(text(), 'Home')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    /**
     * Verify home page is loaded
     *
     * @return true if home page heading is visible
     */
    public boolean isHomePageLoaded() {
        try {
            return driver.findElement(homePageHeading).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click on Login/Signup link
     */
    public void clickLoginLink() {
        WebElement loginElement = waitUtils.waitForElementToBeClickable(loginLink);
        loginElement.click();
    }

    /**
     * Click on Products link
     */
    public void clickProductsLink() {
        WebElement productsElement = waitUtils.waitForElementToBeClickable(productsLink);
        productsElement.click();
    }

    /**
     * Click on Cart link
     */
    public void clickCartLink() {
        WebElement cartElement = waitUtils.waitForElementToBeClickable(cartLink);
        cartElement.click();
    }

    /**
     * Click on Logout link
     */
    public void clickLogoutLink() {
        WebElement logoutElement = waitUtils.waitForElementToBeClickable(logoutLink);
        logoutElement.click();
    }

    /**
     * Verify user is logged in by checking if logout link is visible
     *
     * @return true if logout link is visible
     */
    public boolean isUserLoggedIn() {
        try {
            return driver.findElement(logoutLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get current page URL
     *
     * @return Current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get page title
     *
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}

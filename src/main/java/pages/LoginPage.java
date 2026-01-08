package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

/**
 * LoginPage - Page Object for Login/Signup Page
 * Contains all elements and methods related to login functionality
 */
public class LoginPage {
    private WebDriver driver;
    private WaitUtils waitUtils;

    // Login Form Locators
    private By emailInputField = By.xpath("//input[@data-qa='login-email']");
    private By passwordInputField = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    private By loginErrorMessage = By.xpath("//p[contains(text(), 'Your email or password is incorrect')]");
    private By loginPageTitle = By.xpath("//h2[contains(text(), 'Login to your account')]");

    // Signup Form Locators
    private By signupNameField = By.xpath("//input[@data-qa='signup-name']");
    private By signupEmailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    /**
     * Verify login page is loaded
     *
     * @return true if login page title is visible
     */
    public boolean isLoginPageLoaded() {
        try {
            return driver.findElement(loginPageTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Enter email in login form
     *
     * @param email - Email address
     */
    public void enterLoginEmail(String email) {
        WebElement emailField = waitUtils.waitForElementToBeVisible(emailInputField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    /**
     * Enter password in login form
     *
     * @param password - Password
     */
    public void enterLoginPassword(String password) {
        WebElement passwordField = driver.findElement(passwordInputField);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        WebElement loginBtn = waitUtils.waitForElementToBeClickable(loginButton);
        loginBtn.click();
    }

    /**
     * Perform login
     *
     * @param email - Email address
     * @param password - Password
     */
    public void login(String email, String password) {
        enterLoginEmail(email);
        enterLoginPassword(password);
        clickLoginButton();
    }

    /**
     * Verify login error message
     *
     * @return true if error message is displayed
     */
    public boolean isLoginErrorDisplayed() {
        try {
            return driver.findElement(loginErrorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get login error message text
     *
     * @return Error message text
     */
    public String getLoginErrorMessage() {
        try {
            return driver.findElement(loginErrorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Enter name for signup
     *
     * @param name - Name
     */
    public void enterSignupName(String name) {
        WebElement nameField = waitUtils.waitForElementToBeVisible(signupNameField);
        nameField.sendKeys(name);
    }

    /**
     * Enter email for signup
     *
     * @param email - Email address
     */
    public void enterSignupEmail(String email) {
        WebElement emailField = driver.findElement(signupEmailField);
        emailField.sendKeys(email);
    }

    /**
     * Click signup button
     */
    public void clickSignupButton() {
        WebElement signupBtn = waitUtils.waitForElementToBeClickable(signupButton);
        signupBtn.click();
    }
}

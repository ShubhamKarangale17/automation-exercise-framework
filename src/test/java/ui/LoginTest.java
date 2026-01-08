package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import config.ConfigReader;
import pages.HomePage;
import pages.LoginPage;
import utils.ReportManager;

/**
 * LoginTest - Test cases for login functionality
 */
public class LoginTest extends BaseTest {

    /**
     * Test successful login with valid credentials
     */
    @Test(description = "Verify user can login with valid credentials")
    public void testLoginWithValidCredentials() {
        ReportManager.logInfo("Starting login test with valid credentials");

        // Navigate to home page
        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page failed to load");
        ReportManager.logPass("Home page loaded successfully");

        // Navigate to login page
        homePage.clickLoginLink();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page failed to load");
        ReportManager.logPass("Login page loaded successfully");

        // Perform login
        String validEmail = ConfigReader.getValidEmail();
        String validPassword = ConfigReader.getValidPassword();
        loginPage.login(validEmail, validPassword);
        ReportManager.logInfo("Login performed with email: " + validEmail);

        // Wait for redirect and verify
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUserLoggedIn(), "User failed to login");
        ReportManager.logPass("User successfully logged in");
    }

    /**
     * Test login with invalid credentials
     */
    @Test(description = "Verify login fails with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        ReportManager.logInfo("Starting login test with invalid credentials");

        // Navigate to home page
        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page failed to load");
        ReportManager.logPass("Home page loaded successfully");

        // Navigate to login page
        homePage.clickLoginLink();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page failed to load");
        ReportManager.logPass("Login page loaded successfully");

        // Perform login with invalid credentials
        String invalidEmail = ConfigReader.getInvalidEmail();
        String invalidPassword = ConfigReader.getInvalidPassword();
        loginPage.login(invalidEmail, invalidPassword);
        ReportManager.logInfo("Login attempted with invalid email: " + invalidEmail);

        // Verify error message
        Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Error message not displayed");
        String errorMessage = loginPage.getLoginErrorMessage();
        Assert.assertTrue(errorMessage.contains("incorrect"), "Expected error message not found");
        ReportManager.logPass("Login correctly failed with invalid credentials. Error: " + errorMessage);
    }

    /**
     * Test homepage loads successfully
     */
    @Test(description = "Verify homepage loads successfully")
    public void testHomepageLoads() {
        ReportManager.logInfo("Starting homepage load test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page failed to load");
        ReportManager.logPass("Home page loaded successfully");

        String pageTitle = homePage.getPageTitle();
        ReportManager.logInfo("Page title: " + pageTitle);
        Assert.assertNotNull(pageTitle, "Page title is null");
        ReportManager.logPass("Page title verified");
    }

    /**
     * Test logout functionality
     */
    @Test(description = "Verify user can logout successfully")
    public void testLogout() {
        ReportManager.logInfo("Starting logout test");

        // Login first
        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getValidEmail(), ConfigReader.getValidPassword());
        ReportManager.logInfo("User logged in successfully");

        // Verify user is logged in
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUserLoggedIn(), "User not logged in");
        ReportManager.logPass("User login verified");

        // Logout
        homePage.clickLogoutLink();
        ReportManager.logInfo("Logout button clicked");

        // Verify user is logged out
        Assert.assertFalse(homePage.isUserLoggedIn(), "User still appears to be logged in");
        ReportManager.logPass("User successfully logged out");
    }
}

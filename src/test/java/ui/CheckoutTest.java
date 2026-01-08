package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import config.ConfigReader;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductsPage;
import utils.ReportManager;

/**
 * CheckoutTest - Test cases for checkout functionality
 */
public class CheckoutTest extends BaseTest {

    /**
     * Test checkout page loads successfully
     */
    @Test(description = "Verify checkout page loads successfully")
    public void testCheckoutPageLoads() {
        ReportManager.logInfo("Starting checkout page load test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page failed to load");
        ReportManager.logPass("Home page loaded successfully");

        // Add product to cart first
        homePage.clickProductsLink();
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPageLoaded(), "Products page failed to load");
        productsPage.addFirstProductToCart();
        ReportManager.logInfo("Product added to cart");

        // Navigate to cart and checkout
        productsPage.clickViewCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page failed to load");
        ReportManager.logPass("Cart page loaded");

        cartPage.clickProceedCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page failed to load");
        ReportManager.logPass("Checkout page loaded successfully");
    }

    /**
     * Test filling checkout form
     */
    @Test(description = "Verify checkout form can be filled with correct details")
    public void testFillCheckoutForm() {
        ReportManager.logInfo("Starting fill checkout form test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.clickViewCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickProceedCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page failed to load");
        ReportManager.logPass("Checkout page loaded");

        // Fill checkout form
        checkoutPage.fillCheckoutForm(
            "John",
            "Doe",
            "123 Main Street",
            "California",
            "Los Angeles",
            "90001",
            "1234567890"
        );
        ReportManager.logInfo("Checkout form filled with test data");

        // Verify first name
        String firstName = checkoutPage.getFirstNameValue();
        Assert.assertEquals(firstName, "John", "First name not filled correctly");
        ReportManager.logPass("Checkout form verified successfully");
    }

    /**
     * Test adding comment to order
     */
    @Test(description = "Verify comment can be added to order")
    public void testAddOrderComment() {
        ReportManager.logInfo("Starting add order comment test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.clickViewCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickProceedCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page failed to load");

        String comment = "Please deliver between 9 AM to 5 PM";
        checkoutPage.addOrderComment(comment);
        ReportManager.logInfo("Order comment added: " + comment);
        ReportManager.logPass("Order comment added successfully");
    }

    /**
     * Test complete checkout flow
     */
    @Test(description = "Verify complete checkout flow")
    public void testCompleteCheckoutFlow() {
        ReportManager.logInfo("Starting complete checkout flow test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page failed to load");
        ReportManager.logPass("Home page loaded");

        homePage.clickProductsLink();
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPageLoaded(), "Products page failed to load");
        productsPage.addFirstProductToCart();
        ReportManager.logPass("Product added to cart");

        productsPage.clickViewCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page failed to load");
        Assert.assertTrue(cartPage.getCartItemCount() > 0, "No items in cart");
        ReportManager.logPass("Cart verified with items");

        cartPage.clickProceedCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page failed to load");
        ReportManager.logPass("Checkout page loaded");

        checkoutPage.fillCheckoutForm(
            "Jane",
            "Smith",
            "456 Oak Avenue",
            "Texas",
            "Houston",
            "77001",
            "9876543210"
        );
        ReportManager.logInfo("Checkout form completed");

        checkoutPage.addOrderComment("Special gift for birthday");
        ReportManager.logPass("Complete checkout flow executed successfully");
    }
}

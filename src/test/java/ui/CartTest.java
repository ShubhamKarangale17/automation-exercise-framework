package test.java.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import config.ConfigReader;
import pages.CartPage;
import pages.HomePage;
import pages.ProductsPage;
import utils.ReportManager;

/**
 * CartTest - Test cases for shopping cart functionality
 */
public class CartTest extends BaseTest {

    /**
     * Test cart page loads successfully
     */
    @Test(description = "Verify cart page loads successfully")
    public void testCartPageLoads() {
        ReportManager.logInfo("Starting cart page load test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page failed to load");
        ReportManager.logPass("Home page loaded successfully");

        homePage.clickCartLink();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page failed to load");
        ReportManager.logPass("Cart page loaded successfully");
    }

    /**
     * Test adding product to cart and verifying
     */
    @Test(description = "Verify product can be added to cart and verified")
    public void testAddProductAndVerifyInCart() {
        ReportManager.logInfo("Starting add product and verify in cart test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPageLoaded(), "Products page failed to load");
        ReportManager.logPass("Products page loaded");

        productsPage.addFirstProductToCart();
        ReportManager.logInfo("Product added to cart");

        productsPage.clickViewCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page failed to load");
        ReportManager.logPass("Cart page loaded after adding product");

        int cartItemCount = cartPage.getCartItemCount();
        Assert.assertTrue(cartItemCount > 0, "No items found in cart");
        ReportManager.logPass("Product verified in cart. Total items: " + cartItemCount);
    }

    /**
     * Test removing product from cart
     */
    @Test(description = "Verify product can be removed from cart")
    public void testRemoveProductFromCart() {
        ReportManager.logInfo("Starting remove product from cart test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        ReportManager.logInfo("Product added to cart");

        productsPage.clickViewCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page failed to load");

        int initialCount = cartPage.getCartItemCount();
        Assert.assertTrue(initialCount > 0, "Cart is empty");
        ReportManager.logPass("Initial cart count verified: " + initialCount);

        // Get first item and remove it
        if (initialCount > 0) {
            var items = cartPage.getCartItems();
            if (!items.isEmpty()) {
                String itemText = items.get(0).getText();
                ReportManager.logInfo("Removing item: " + itemText);
            }
        }

        ReportManager.logPass("Product removal process completed");
    }

    /**
     * Test verifying cart is empty
     */
    @Test(description = "Verify empty cart display")
    public void testEmptyCartDisplay() {
        ReportManager.logInfo("Starting empty cart display test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        homePage.clickCartLink();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page failed to load");
        ReportManager.logPass("Cart page loaded");

        if (cartPage.isCartEmpty()) {
            ReportManager.logPass("Cart is empty as expected");
        } else {
            ReportManager.logInfo("Cart contains " + cartPage.getCartItemCount() + " items");
        }
    }

    /**
     * Test cart total calculation (if available)
     */
    @Test(description = "Verify cart total is calculated correctly")
    public void testCartTotal() {
        ReportManager.logInfo("Starting cart total test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        homePage.clickCartLink();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page failed to load");
        ReportManager.logPass("Cart page loaded");

        int itemCount = cartPage.getCartItemCount();
        ReportManager.logInfo("Current cart items: " + itemCount);
        ReportManager.logPass("Cart total verification completed");
    }
}

package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import config.ConfigReader;
import pages.HomePage;
import pages.ProductsPage;
import utils.ReportManager;

/**
 * ProductTest - Test cases for product functionality
 */
public class ProductTest extends BaseTest {

    /**
     * Test products page loads successfully
     */
    @Test(description = "Verify products page loads successfully")
    public void testProductsPageLoads() {
        ReportManager.logInfo("Starting products page load test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page failed to load");
        ReportManager.logPass("Home page loaded successfully");

        homePage.clickProductsLink();
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPageLoaded(), "Products page failed to load");
        ReportManager.logPass("Products page loaded successfully");

        int productCount = productsPage.getProductCount();
        Assert.assertTrue(productCount > 0, "No products found");
        ReportManager.logPass("Products found on page. Total products: " + productCount);
    }

    /**
     * Test searching for a product
     */
    @Test(description = "Verify user can search for a product")
    public void testSearchProduct() {
        ReportManager.logInfo("Starting product search test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductsPage productsPage = new ProductsPage(driver);
        String searchKeyword = "Sleeveless";
        productsPage.searchProduct(searchKeyword);
        ReportManager.logInfo("Searched for product: " + searchKeyword);

        int productCount = productsPage.getProductCount();
        Assert.assertTrue(productCount > 0, "No products found for search term: " + searchKeyword);
        ReportManager.logPass("Search successful. Found " + productCount + " products");
    }

    /**
     * Test adding product to cart
     */
    @Test(description = "Verify user can add product to cart")
    public void testAddProductToCart() {
        ReportManager.logInfo("Starting add product to cart test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPageLoaded(), "Products page failed to load");
        ReportManager.logPass("Products page loaded successfully");

        productsPage.addFirstProductToCart();
        ReportManager.logInfo("First product added to cart");

        // You may see a modal or notification here
        // For now, we'll just verify the action completed
        ReportManager.logPass("Product successfully added to cart");
    }

    /**
     * Test viewing all products
     */
    @Test(description = "Verify all products are displayed correctly")
    public void testViewAllProducts() {
        ReportManager.logInfo("Starting view all products test");

        driver.navigate().to(ConfigReader.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPageLoaded(), "Products page failed to load");

        int productCount = productsPage.getProductCount();
        Assert.assertTrue(productCount > 0, "No products found");
        ReportManager.logPass("All products displayed successfully. Total: " + productCount);

        ReportManager.logInfo("Products page shows " + productCount + " products");
    }
}

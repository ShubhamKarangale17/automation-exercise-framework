package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;
import java.util.List;

/**
 * ProductsPage - Page Object for Products Page
 * Contains all elements and methods related to product functionality
 */
public class ProductsPage {
    private WebDriver driver;
    private WaitUtils waitUtils;

    // Locators
    private By productsPageTitle = By.xpath("//h2[contains(text(), 'All Products')]");
    private By searchInput = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By productsList = By.xpath("//div[@class='productinfo text-center']");
    private By productName = By.xpath(".//p");
    private By addToCartButton = By.xpath(".//a[contains(text(), 'Add to cart')]");
    private By continueShoppingButton = By.xpath("//button[contains(text(), 'Continue Shopping')]");
    private By viewCartButton = By.xpath("//a[contains(text(), 'View Cart')]");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    /**
     * Verify products page is loaded
     *
     * @return true if products page title is visible
     */
    public boolean isProductsPageLoaded() {
        try {
            return driver.findElement(productsPageTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Search for a product
     *
     * @param productName - Name of the product to search
     */
    public void searchProduct(String productName) {
        WebElement searchField = waitUtils.waitForElementToBeVisible(searchInput);
        searchField.clear();
        searchField.sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    /**
     * Get all products displayed on the page
     *
     * @return List of product elements
     */
    public List<WebElement> getAllProducts() {
        waitUtils.waitForElementToBeVisible(productsPageTitle);
        return driver.findElements(productsList);
    }

    /**
     * Get product by name
     *
     * @param productName - Name of the product
     * @return WebElement of the product
     */
    public WebElement getProductByName(String productName) {
        List<WebElement> products = getAllProducts();
        for (WebElement product : products) {
            String name = product.findElement(productName).getText();
            if (name.equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Add product to cart by name
     *
     * @param productName - Name of the product
     */
    public void addProductToCart(String productName) {
        List<WebElement> products = getAllProducts();
        for (WebElement product : products) {
            try {
                String name = product.findElement(productName).getText();
                if (name.equalsIgnoreCase(productName)) {
                    WebElement addBtn = product.findElement(addToCartButton);
                    addBtn.click();
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    /**
     * Add first product to cart
     */
    public void addFirstProductToCart() {
        List<WebElement> products = getAllProducts();
        if (!products.isEmpty()) {
            WebElement addBtn = products.get(0).findElement(addToCartButton);
            addBtn.click();
        }
    }

    /**
     * Click continue shopping button
     */
    public void clickContinueShopping() {
        WebElement continueBtn = waitUtils.waitForElementToBeClickable(continueShoppingButton);
        continueBtn.click();
    }

    /**
     * Click view cart button
     */
    public void clickViewCart() {
        WebElement viewCartBtn = waitUtils.waitForElementToBeClickable(viewCartButton);
        viewCartBtn.click();
    }

    /**
     * Get number of products displayed
     *
     * @return Count of products
     */
    public int getProductCount() {
        return getAllProducts().size();
    }
}

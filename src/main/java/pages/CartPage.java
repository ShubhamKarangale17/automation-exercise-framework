package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;
import java.util.List;

/**
 * CartPage - Page Object for Shopping Cart Page
 * Contains all elements and methods related to shopping cart
 */
public class CartPage {
    private WebDriver driver;
    private WaitUtils waitUtils;

    // Locators
    private By cartPageTitle = By.xpath("//h2[contains(text(), 'Shopping Cart')]");
    private By cartTable = By.xpath("//table[@class='table table-condensed table-striped']");
    private By cartItems = By.xpath("//tr[@class='cart_item']");
    private By itemPrice = By.xpath(".//td[@class='cart_price']/p");
    private By itemQuantity = By.xpath(".//td[@class='cart_quantity']/button");
    private By removeButton = By.xpath(".//a[@class='cart_quantity_delete']");
    private By proceedCheckoutButton = By.xpath("//a[@class='btn btn-default check_out']");
    private By continueShopping = By.xpath("//a[@class='btn btn-default']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    /**
     * Verify cart page is loaded
     *
     * @return true if cart page title is visible
     */
    public boolean isCartPageLoaded() {
        try {
            return driver.findElement(cartPageTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get all cart items
     *
     * @return List of cart item elements
     */
    public List<WebElement> getCartItems() {
        waitUtils.waitForElementToBeVisible(cartPageTitle);
        return driver.findElements(cartItems);
    }

    /**
     * Get number of items in cart
     *
     * @return Count of items in cart
     */
    public int getCartItemCount() {
        return getCartItems().size();
    }

    /**
     * Verify product is in cart
     *
     * @param productName - Name of the product
     * @return true if product is found in cart
     */
    public boolean isProductInCart(String productName) {
        List<WebElement> items = getCartItems();
        for (WebElement item : items) {
            String itemText = item.getText();
            if (itemText.contains(productName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get product price from cart
     *
     * @param productName - Name of the product
     * @return Price of the product
     */
    public String getProductPrice(String productName) {
        List<WebElement> items = getCartItems();
        for (WebElement item : items) {
            String itemText = item.getText();
            if (itemText.contains(productName)) {
                return item.findElement(itemPrice).getText();
            }
        }
        return "";
    }

    /**
     * Remove product from cart
     *
     * @param productName - Name of the product to remove
     */
    public void removeProductFromCart(String productName) {
        List<WebElement> items = getCartItems();
        for (WebElement item : items) {
            String itemText = item.getText();
            if (itemText.contains(productName)) {
                WebElement removeBtn = item.findElement(removeButton);
                removeBtn.click();
                break;
            }
        }
    }

    /**
     * Click proceed to checkout button
     */
    public void clickProceedCheckout() {
        WebElement proceedBtn = waitUtils.waitForElementToBeClickable(proceedCheckoutButton);
        proceedBtn.click();
    }

    /**
     * Click continue shopping button
     */
    public void clickContinueShopping() {
        WebElement continueBtn = waitUtils.waitForElementToBeClickable(continueShopping);
        continueBtn.click();
    }

    /**
     * Verify cart is empty
     *
     * @return true if cart has no items
     */
    public boolean isCartEmpty() {
        return getCartItemCount() == 0;
    }
}

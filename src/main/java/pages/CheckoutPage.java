package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

/**
 * CheckoutPage - Page Object for Checkout Page
 * Contains all elements and methods related to checkout process
 */
public class CheckoutPage {
    private WebDriver driver;
    private WaitUtils waitUtils;

    // Locators
    private By checkoutPageTitle = By.xpath("//h2[contains(text(), 'Review Your Order')]");
    private By firstNameInput = By.xpath("//input[@name='first_name']");
    private By lastNameInput = By.xpath("//input[@name='last_name']");
    private By addressInput = By.xpath("//textarea[@name='address1']");
    private By countrySelect = By.xpath("//select[@name='country']");
    private By stateInput = By.xpath("//input[@name='state']");
    private By cityInput = By.xpath("//input[@name='city']");
    private By zipCodeInput = By.xpath("//input[@name='zipcode']");
    private By phoneInput = By.xpath("//input[@name='mobile_number']");
    private By placeOrderButton = By.xpath("//a[@data-qa='place-order']");
    private By commentTextarea = By.xpath("//textarea[@name='comment']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    /**
     * Verify checkout page is loaded
     *
     * @return true if checkout page title is visible
     */
    public boolean isCheckoutPageLoaded() {
        try {
            return driver.findElement(checkoutPageTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Fill checkout form with address details
     *
     * @param firstName - First name
     * @param lastName - Last name
     * @param address - Address
     * @param state - State
     * @param city - City
     * @param zipCode - Zip code
     * @param phone - Phone number
     */
    public void fillCheckoutForm(String firstName, String lastName, String address,
                                 String state, String city, String zipCode, String phone) {
        WebElement firstNameField = waitUtils.waitForElementToBeVisible(firstNameInput);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = driver.findElement(lastNameInput);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        WebElement addressField = driver.findElement(addressInput);
        addressField.clear();
        addressField.sendKeys(address);

        WebElement stateField = driver.findElement(stateInput);
        stateField.clear();
        stateField.sendKeys(state);

        WebElement cityField = driver.findElement(cityInput);
        cityField.clear();
        cityField.sendKeys(city);

        WebElement zipField = driver.findElement(zipCodeInput);
        zipField.clear();
        zipField.sendKeys(zipCode);

        WebElement phoneField = driver.findElement(phoneInput);
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    /**
     * Add comment to order
     *
     * @param comment - Comment text
     */
    public void addOrderComment(String comment) {
        WebElement commentField = driver.findElement(commentTextarea);
        commentField.sendKeys(comment);
    }

    /**
     * Click place order button
     */
    public void clickPlaceOrder() {
        WebElement placeOrderBtn = waitUtils.waitForElementToBeClickable(placeOrderButton);
        placeOrderBtn.click();
    }

    /**
     * Get first name field value
     *
     * @return Value of first name field
     */
    public String getFirstNameValue() {
        return driver.findElement(firstNameInput).getAttribute("value");
    }

    /**
     * Get last name field value
     *
     * @return Value of last name field
     */
    public String getLastNameValue() {
        return driver.findElement(lastNameInput).getAttribute("value");
    }
}

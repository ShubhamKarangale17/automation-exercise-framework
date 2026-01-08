# Quick Reference Guide - Automation Exercise Framework

## 🚀 Quick Start (5 Minutes)

### 1. Import Project in Eclipse
```
File → Import → Existing Maven Projects → Select project folder → Finish
```

### 2. Update Credentials
Edit `src/test/resources/config.properties`:
```properties
valid_email=your-email@example.com
valid_password=your-password
```

### 3. Run Tests
```
Right-click on testng.xml → Run As → TestNG Suite
```

### 4. View Reports
Open `reports/extent-report-[timestamp].html` in browser

---

## 📁 File Navigation Quick Reference

| Purpose | File | Path |
|---------|------|------|
| Add WebDriver code | DriverFactory | `src/main/java/utils/DriverFactory.java` |
| Add page interactions | LoginPage | `src/main/java/pages/LoginPage.java` |
| Write UI tests | LoginTest | `src/test/java/ui/LoginTest.java` |
| Write API tests | GetProductsTest | `src/test/java/api/GetProductsTest.java` |
| Change URLs/timeouts | config.properties | `src/test/resources/config.properties` |
| Add test data | testdata.json | `src/test/resources/testdata.json` |
| Configure tests | testng.xml | `src/test/runners/testng.xml` |
| Build config | pom.xml | `pom.xml` (root) |

---

## 🔑 Key Class Locations

### Configuration & Base
```
ConfigReader         → src/main/java/config/ConfigReader.java
BaseTest            → src/main/java/base/BaseTest.java
BaseAPITest         → src/main/java/base/BaseAPITest.java
```

### Page Objects
```
HomePage            → src/main/java/pages/HomePage.java
LoginPage           → src/main/java/pages/LoginPage.java
ProductsPage        → src/main/java/pages/ProductsPage.java
CartPage            → src/main/java/pages/CartPage.java
CheckoutPage        → src/main/java/pages/CheckoutPage.java
```

### Utilities
```
DriverFactory       → src/main/java/utils/DriverFactory.java
WaitUtils           → src/main/java/utils/WaitUtils.java
ScreenshotUtils     → src/main/java/utils/ScreenshotUtils.java
TestDataUtils       → src/main/java/utils/TestDataUtils.java
ReportManager       → src/main/java/utils/ReportManager.java
AssertionUtils      → src/main/java/utils/AssertionUtils.java
```

### Test Classes
```
UI Tests:
  LoginTest         → src/test/java/ui/LoginTest.java
  ProductTest       → src/test/java/ui/ProductTest.java
  CartTest          → src/test/java/ui/CartTest.java
  CheckoutTest      → src/test/java/ui/CheckoutTest.java

API Tests:
  GetProductsTest   → src/test/java/api/GetProductsTest.java
  CreateCartTest    → src/test/java/api/CreateCartTest.java
  UpdateCartTest    → src/test/java/api/UpdateCartTest.java
  DeleteCartTest    → src/test/java/api/DeleteCartTest.java
```

---

## ⚙️ Common Configuration Changes

### Change Browser Type
Edit `src/test/resources/config.properties`:
```properties
# Current:
browser=chrome

# To use Firefox in future:
browser=firefox
```

### Change Application URL
Edit `src/test/resources/config.properties`:
```properties
# Current:
base_url=https://automationexercise.com/

# To test staging:
base_url=https://staging.automationexercise.com/
```

### Change Timeout Values
Edit `src/test/resources/config.properties`:
```properties
# Current (in seconds):
implicit_wait=10
explicit_wait=15
page_load_timeout=30

# Increase for slower environments:
implicit_wait=20
explicit_wait=30
page_load_timeout=60
```

### Enable Headless Mode
Edit `src/test/resources/config.properties`:
```properties
# Current:
headless=false

# For CI/CD:
headless=true
```

---

## 🧪 Writing a New Test - Step by Step

### Step 1: Create Test Class
```java
package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HomePage;

public class MyNewTest extends BaseTest {
    
    @Test(description = "What does this test do?")
    public void testMyScenario() {
        // Test code here
    }
}
```

### Step 2: Use Page Objects
```java
driver.navigate().to(ConfigReader.getBaseUrl());
HomePage homePage = new HomePage(driver);
Assert.assertTrue(homePage.isHomePageLoaded());
```

### Step 3: Add Assertions
```java
Assert.assertEquals(actualValue, expectedValue, "Assertion message");
Assert.assertTrue(condition, "Message if false");
Assert.assertFalse(condition, "Message if true");
```

### Step 4: Add Reporting
```java
ReportManager.logInfo("Informational message");
ReportManager.logPass("Test passed step");
ReportManager.logFail("Test failed reason");
```

### Step 5: Register in testng.xml
```xml
<class name="ui.MyNewTest">
    <methods>
        <include name="testMyScenario"/>
    </methods>
</class>
```

---

## 🔌 Writing a New Page Object - Step by Step

### Step 1: Create Page Class
```java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class MyNewPage {
    private WebDriver driver;
    private WaitUtils waitUtils;
    
    public MyNewPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }
}
```

### Step 2: Add Locators
```java
private By pageTitle = By.xpath("//h1[contains(text(), 'My Page')]");
private By submitButton = By.id("submit-btn");
private By inputField = By.name("input-field");
```

### Step 3: Add Methods
```java
public boolean isPageLoaded() {
    try {
        return driver.findElement(pageTitle).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public void fillForm(String value) {
    WebElement input = waitUtils.waitForElementToBeVisible(inputField);
    input.clear();
    input.sendKeys(value);
}

public void submit() {
    WebElement submitBtn = waitUtils.waitForElementToBeClickable(submitButton);
    submitBtn.click();
}
```

### Step 4: Use in Tests
```java
MyNewPage myPage = new MyNewPage(driver);
Assert.assertTrue(myPage.isPageLoaded());
myPage.fillForm("Test Value");
myPage.submit();
```

---

## 🌐 Writing a New API Test - Step by Step

### Step 1: Create Test Class
```java
package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import config.ConfigReader;

public class MyNewAPITest {
    
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigReader.getApiBaseUrl();
    }
}
```

### Step 2: Add Test Methods
```java
@Test(description = "Verify API endpoint works")
public void testMyAPIEndpoint() {
    Response response = RestAssured.given()
            .timeout(ConfigReader.getApiTimeout())
            .when()
            .get("/my-endpoint")
            .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .response();
    
    Assert.assertEquals(response.getStatusCode(), 200);
}
```

### Step 3: Validate Response
```java
String responseBody = response.getBody().asString();
Assert.assertTrue(responseBody.contains("expectedField"));

// Or use JSON Path
String name = response.jsonPath().getString("name");
Assert.assertEquals(name, "expectedName");
```

### Step 4: Add to testng.xml
```xml
<class name="api.MyNewAPITest">
    <methods>
        <include name="testMyAPIEndpoint"/>
    </methods>
</class>
```

---

## 🛠 Common Code Snippets

### Wait for Element
```java
WebElement element = waitUtils.waitForElementToBeVisible(locator);
WebElement element = waitUtils.waitForElementToBeClickable(locator);
WebElement element = waitUtils.waitForElementToBePresent(locator);
```

### Get Property Value
```java
String url = ConfigReader.getBaseUrl();
int timeout = ConfigReader.getExplicitWait();
boolean headless = ConfigReader.isHeadless();
```

### Log Messages
```java
ReportManager.logInfo("Information message");
ReportManager.logPass("Step passed");
ReportManager.logFail("Step failed");
ReportManager.logSkip("Test skipped");
```

### Get Test Data
```java
String email = TestDataUtils.getLoginTestData(0, "email");
String productName = TestDataUtils.getProductTestData(0, "productName");
```

### API Assertion
```java
Assert.assertEquals(response.getStatusCode(), 200);
Assert.assertTrue(response.getContentType().contains("application/json"));
Assert.assertFalse(response.getBody().asString().isEmpty());
```

### Take Screenshot
```java
String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "TestName");
ReportManager.attachScreenshot(screenshotPath);
```

---

## 🚀 Running Tests - Different Ways

### Maven Command Line
```bash
# All tests
mvn clean test

# UI tests only
mvn test -Dtest=ui.*

# API tests only
mvn test -Dtest=api.*

# Specific test class
mvn test -Dtest=ui.LoginTest

# Specific test method
mvn test -Dtest=ui.LoginTest#testLoginWithValidCredentials
```

### Eclipse
```
1. Right-click testng.xml
2. Run As → TestNG Suite
```

### Jenkins
```
1. Click Build Now
2. Monitor Console Output
3. View reports in Artifacts
```

---

## 📊 Test Execution & Reports

### After Test Execution
1. Open `reports/` folder
2. Find latest `extent-report-*.html` file
3. Open in browser to view:
   - Test status (Pass/Fail)
   - Execution time
   - Screenshots
   - Logs
   - Environment info

### Report Features
- ✅ Visual test results
- ✅ Screenshot attachments
- ✅ Detailed logs
- ✅ Execution timeline
- ✅ Device/Browser info

---

## 🐛 Debugging Tips

### Enable Logging
Edit `src/test/resources/config.properties`:
```properties
log_level=DEBUG
```

### Take Manual Screenshot
```java
ScreenshotUtils.captureScreenshot(driver, "DebugScreenshot");
```

### Print Response
```java
System.out.println(response.getBody().prettyPrint());
```

### Check Page Source
```java
System.out.println(driver.getPageSource());
```

### Verify Locator
```java
List<WebElement> elements = driver.findElements(locator);
System.out.println("Found " + elements.size() + " elements");
```

---

## 📚 Important Methods by Class

### BaseTest
- `setUp()` - Runs before each test
- `tearDown()` - Runs after each test
- `driver` - WebDriver instance

### ConfigReader
- `getBaseUrl()` - Application URL
- `getApiBaseUrl()` - API URL
- `getExplicitWait()` - Wait timeout
- `getValidEmail()` - Test email
- `getValidPassword()` - Test password

### DriverFactory
- `initializeDriver()` - Start WebDriver
- `getDriver()` - Get current WebDriver
- `quitDriver()` - Close WebDriver

### WaitUtils
- `waitForElementToBeVisible(locator)`
- `waitForElementToBeClickable(locator)`
- `waitForElementToBePresent(locator)`
- `waitForElementToDisappear(locator)`
- `waitForTextInElement(locator, text)`

### ReportManager
- `startTest(name, description)`
- `logPass(message)`
- `logFail(message)`
- `logInfo(message)`
- `attachScreenshot(path)`
- `flushReport()`

---

## ✅ Checklist Before Committing Code

- ✅ All tests pass locally
- ✅ No hardcoded values (use config.properties)
- ✅ Page objects for all pages
- ✅ Meaningful assertion messages
- ✅ Proper exception handling
- ✅ Screenshots on failures
- ✅ Code formatted correctly
- ✅ No sensitive data committed
- ✅ Updated testng.xml with new tests
- ✅ README updated if needed

---

## 🆘 Quick Troubleshooting

| Problem | Solution |
|---------|----------|
| Tests can't find elements | Increase wait time in config.properties |
| WebDriver not downloading | Check internet connection, clear cache |
| Tests hanging | Add timeout to operations |
| Reports not generating | Check reports/ directory permissions |
| API requests fail | Verify base URL in config.properties |
| Jenkins build fails | Check console output for detailed error |

---

## 📞 Need Help?

1. Check README.md for detailed documentation
2. Review test examples in existing tests
3. Check PROJECT_SUMMARY.md for overview
4. Look for similar test code as reference
5. Enable logging for debugging

---

**Last Updated**: January 8, 2026
**Framework Version**: 1.0.0

Happy Testing! 🎉

# Automation Exercise Framework

## 📋 Project Overview

A comprehensive, production-ready automation testing framework that combines UI automation, API automation, and CI/CD integration. This framework follows industry best practices and Page Object Model architecture, designed to be interview-ready and scalable.

### Key Features
- ✅ **Hybrid Testing**: Selenium WebDriver (UI) + Rest Assured (API)
- ✅ **Page Object Model**: Clean, maintainable code structure
- ✅ **TestNG Framework**: Organized test execution and reporting
- ✅ **Maven Build Tool**: Dependency management and build automation
- ✅ **Extent Reports**: Beautiful HTML test reports
- ✅ **Jenkins CI/CD**: Automated pipeline for continuous testing
- ✅ **Postman Integration**: API design and validation
- ✅ **Centralized Configuration**: Properties-based configuration
- ✅ **Screenshot Capture**: Automatic screenshot on failures
- ✅ **ThreadLocal Driver**: Thread-safe WebDriver management

---

## 🛠 Technology Stack

| Technology | Purpose | Version |
|-----------|---------|---------|
| Java | Programming Language | 11+ |
| Selenium WebDriver | UI Automation | 4.15.0 |
| TestNG | Test Framework | 7.8.1 |
| Rest Assured | API Automation | 5.3.2 |
| Maven | Build Tool | 3.8.1+ |
| WebDriver Manager | Driver Management | 5.6.3 |
| Extent Reports | Reporting | 5.1.1 |
| Jenkins | CI/CD Pipeline | 2.x |
| Postman | API Testing (Manual) | Latest |
| Eclipse IDE | Development Environment | Latest |

---

## 📁 Project Structure

```
automation-exercise-framework/
│
├── pom.xml                           # Maven configuration with dependencies
├── Jenkinsfile                       # CI/CD pipeline configuration
├── README.md                         # Project documentation
│
├── src/main/java/
│   ├── base/
│   │   └── BaseTest.java             # Base class for all tests
│   │
│   ├── config/
│   │   └── ConfigReader.java         # Configuration properties reader
│   │
│   ├── pages/
│   │   ├── HomePage.java             # Home page object model
│   │   ├── LoginPage.java            # Login page object model
│   │   ├── ProductsPage.java         # Products page object model
│   │   ├── CartPage.java             # Cart page object model
│   │   └── CheckoutPage.java         # Checkout page object model
│   │
│   └── utils/
│       ├── DriverFactory.java        # WebDriver factory pattern
│       ├── WaitUtils.java            # Explicit wait utilities
│       ├── ScreenshotUtils.java      # Screenshot capture utility
│       ├── TestDataUtils.java        # Test data reading utility
│       └── ReportManager.java        # Extent Reports manager
│
├── src/test/java/
│   ├── ui/
│   │   ├── LoginTest.java            # Login test cases
│   │   ├── ProductTest.java          # Product test cases
│   │   ├── CartTest.java             # Cart test cases
│   │   └── CheckoutTest.java         # Checkout test cases
│   │
│   ├── api/
│   │   ├── GetProductsTest.java      # GET API test cases
│   │   ├── CreateCartTest.java       # POST/CREATE API test cases
│   │   ├── UpdateCartTest.java       # PUT/UPDATE API test cases
│   │   └── DeleteCartTest.java       # DELETE API test cases
│   │
│   └── runners/
│       └── testng.xml                # TestNG suite configuration
│
├── src/test/resources/
│   ├── config.properties             # Test configuration
│   ├── testdata.json                 # Test data in JSON format
│   │
│   └── postman/
│       └── fakestore-api.postman_collection.json  # Postman collection
│
└── reports/
    ├── extent-report.html            # Extent test report (generated)
    └── screenshots/                  # Screenshots directory (generated)
```

---

## 🚀 Quick Start Guide

### Prerequisites
- Java 11 or higher installed
- Maven 3.8.1+ installed
- Eclipse IDE (recommended)
- ChromeDriver (automatically managed by WebDriverManager)
- Git (for version control)

### Local Setup in Eclipse

#### Step 1: Clone the Repository
```bash
git clone https://github.com/your-username/automation-exercise-framework.git
cd automation-exercise-framework
```

#### Step 2: Import Project in Eclipse
1. Open Eclipse IDE
2. Go to **File → Import → Existing Maven Projects**
3. Select the cloned project directory
4. Click **Finish**
5. Wait for Maven to download dependencies

#### Step 3: Configure Credentials
Edit `src/test/resources/config.properties`:
```properties
# Update with actual test credentials
valid_email=your-email@example.com
valid_password=your-password
invalid_email=invalid@test.com
invalid_password=wrongpassword
```

#### Step 4: Run Tests

**Option A: Run from Eclipse**
1. Right-click on `src/test/runners/testng.xml`
2. Select **Run As → TestNG Suite**

**Option B: Run using Maven**
```bash
# Run all tests
mvn clean test

# Run only UI tests
mvn test -Dtest=ui.*

# Run only API tests
mvn test -Dtest=api.*

# Run with specific browser settings
mvn test -Dheadless=true
```

**Option C: Run individual test class**
```bash
mvn test -Dtest=ui.LoginTest
```

#### Step 5: View Reports
After test execution, open the generated report:
- Navigate to `reports/` folder
- Open `extent-report-[timestamp].html` in browser

---

## 📝 Detailed Test Scenarios

### UI Test Scenarios (automationexercise.com)

#### 1. **Login Tests**
- ✅ Verify homepage loads successfully
- ✅ User login with valid credentials
- ✅ User login with invalid credentials (negative test)
- ✅ Logout functionality verification
- ✅ Error message validation on login failure

**Test Class**: `ui.LoginTest`

```java
// Example: Login with valid credentials
@Test(description = "Verify user can login with valid credentials")
public void testLoginWithValidCredentials() {
    driver.navigate().to(ConfigReader.getBaseUrl());
    HomePage homePage = new HomePage(driver);
    Assert.assertTrue(homePage.isHomePageLoaded());
    
    homePage.clickLoginLink();
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(ConfigReader.getValidEmail(), ConfigReader.getValidPassword());
    
    Assert.assertTrue(homePage.isUserLoggedIn());
}
```

#### 2. **Product Tests**
- ✅ Products page loads successfully
- ✅ Search product functionality
- ✅ Add product to cart
- ✅ View all products
- ✅ Product details verification

**Test Class**: `ui.ProductTest`

#### 3. **Cart Tests**
- ✅ Cart page loads successfully
- ✅ Add product and verify in cart
- ✅ Remove product from cart
- ✅ Empty cart display
- ✅ Cart total calculation

**Test Class**: `ui.CartTest`

#### 4. **Checkout Tests**
- ✅ Checkout page loads successfully
- ✅ Fill checkout form with details
- ✅ Add comment to order
- ✅ Complete checkout flow (end-to-end)
- ✅ Form validation

**Test Class**: `ui.CheckoutTest`

---

### API Test Scenarios (fakestoreapi.com)

#### 1. **GET Products Tests**
- ✅ Get all products (200 OK)
- ✅ Get product by ID (200 OK)
- ✅ Get product with invalid ID (404 handling)
- ✅ Get products with limit parameter
- ✅ Response headers validation
- ✅ Response time verification

**Test Class**: `api.GetProductsTest`

```java
// Example: Get all products
@Test(description = "Verify GET all products returns 200 status code")
public void testGetAllProducts() {
    Response response = RestAssured.given()
            .timeout(ConfigReader.getApiTimeout())
            .when()
            .get("/products")
            .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .response();
    
    Assert.assertEquals(response.getStatusCode(), 200);
}
```

#### 2. **CREATE Cart Tests (POST)**
- ✅ Create cart with multiple products
- ✅ Create cart with single product
- ✅ Create cart with invalid data
- ✅ Verify response contains required fields
- ✅ Cart ID validation

**Test Class**: `api.CreateCartTest`

#### 3. **UPDATE Cart Tests (PUT)**
- ✅ Update existing cart
- ✅ Update cart quantity
- ✅ Add new products to cart
- ✅ Update non-existent cart (error handling)
- ✅ Update with empty products list

**Test Class**: `api.UpdateCartTest`

#### 4. **DELETE Cart Tests**
- ✅ Delete existing cart
- ✅ Delete multiple carts
- ✅ Delete non-existent cart
- ✅ Invalid cart ID format handling
- ✅ Verify deleted cart cannot be accessed

**Test Class**: `api.DeleteCartTest`

---

## 🔧 Page Object Model (POM) Architecture

### Why POM?
- Reduces code duplication
- Improves code maintainability
- Makes tests readable
- Simplifies updates when UI changes

### Example: LoginPage.java

```java
public class LoginPage {
    private WebDriver driver;
    private WaitUtils waitUtils;
    
    // Locators
    private By emailInputField = By.xpath("//input[@data-qa='login-email']");
    private By passwordInputField = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }
    
    // Methods
    public void login(String email, String password) {
        enterLoginEmail(email);
        enterLoginPassword(password);
        clickLoginButton();
    }
    
    public void enterLoginEmail(String email) {
        WebElement emailField = waitUtils.waitForElementToBeVisible(emailInputField);
        emailField.clear();
        emailField.sendKeys(email);
    }
    
    // ... more methods
}
```

---

## 🧪 Rest Assured API Testing Guide

### Best Practices

#### 1. **Request/Response Validation**
```java
Response response = RestAssured.given()
    .timeout(5000)
    .header("Content-Type", "application/json")
    .body(requestPayload)
    .when()
    .post("/api/endpoint")
    .then()
    .assertThat()
    .statusCode(200)
    .body("id", notNullValue())
    .body("name", equalTo("expectedName"))
    .extract()
    .response();
```

#### 2. **JSON Path Assertions**
```java
String productName = response.jsonPath().getString("products[0].name");
int productCount = response.jsonPath().getInt("products.size()");
```

#### 3. **Status Code Verification**
```java
Assert.assertEquals(response.getStatusCode(), 200);
```

#### 4. **Response Header Validation**
```java
String contentType = response.getHeader("Content-Type");
Assert.assertTrue(contentType.contains("application/json"));
```

---

## 🔄 CI/CD Integration with Jenkins

### Jenkinsfile Overview

The project includes a **Jenkinsfile** for automated CI/CD pipeline with following stages:

#### Stage 1: Checkout
- Pulls code from GitHub repository
- Supports branch selection

#### Stage 2: Build
- Executes Maven clean compile
- Resolves dependencies

#### Stage 3: Test Environment Preparation
- Creates necessary directories
- Prepares test data

#### Stage 4: Run Tests (Parallel)
- Runs API tests and UI tests in parallel
- Supports headless mode for CI environment
- Configurable test suite selection

#### Stage 5: Generate Reports
- Creates Extent Reports
- Generates TestNG reports

#### Stage 6: Publish Results
- Archives test reports
- Publishes results to Jenkins dashboard
- Optional: Email/Slack notifications

### Jenkins Setup Instructions

#### Step 1: Create Jenkins Job
1. Open Jenkins Dashboard
2. Click **New Item**
3. Enter job name
4. Select **Pipeline**
5. Click **OK**

#### Step 2: Configure Pipeline
1. In job configuration, under **Pipeline**, select **Pipeline script from SCM**
2. Set SCM to **Git**
3. Enter repository URL: `https://github.com/your-username/automation-exercise-framework.git`
4. Set branch: `main`
5. Set Script Path: `Jenkinsfile`

#### Step 3: Add Build Parameters (Optional)
```
TEST_SUITE: choice parameter (All Tests, UI Tests Only, API Tests Only)
HEADLESS_MODE: boolean parameter (true/false)
```

#### Step 4: Build Triggers
- Trigger on push (if webhook configured)
- Poll SCM: `H H * * *` (daily)
- Manually trigger

#### Step 5: Execute Job
1. Click **Build Now**
2. Monitor build progress in **Console Output**
3. View reports in **Build Artifacts**

#### Jenkins Pipeline Execution

```bash
# Equivalent command to Jenkinsfile
mvn clean compile test \
    -Dbaseurl=https://automationexercise.com \
    -Dapibaseurl=https://fakestoreapi.com \
    -Dheadless=true

# Generate reports
mvn surefire-report:report

# Archive artifacts
cp -r reports/ $WORKSPACE/reports/
```

---

## 📊 Postman API Testing & Export

### Using Postman for API Design & Validation

#### Step 1: Create Workspace
1. Open Postman.
2. Create new workspace: "Automation Exercise"

#### Step 2: Import Provided Collection
1. Click **Import**
2. Select `src/test/resources/postman/fakestore-api.postman_collection.json`
3. Collection imported with all endpoints

#### Step 3: Configure Environment
1. Click **Environment** (gear icon)
2. Create environment: "FakeStore-API"
3. Set variables:
   ```
   base_url = https://fakestoreapi.com
   api_timeout = 5000
   ```

#### Step 4: Test Endpoints Manually
1. Select endpoint from collection
2. Click **Send**
3. Verify response (status, headers, body)
4. Use **Tests** tab to write JavaScript tests

#### Step 5: Export Collection
1. Right-click collection
2. Click **Export**
3. Save as JSON
4. Include in project under `src/test/resources/postman/`

#### Example: Postman Test Script
```javascript
// Tests tab in Postman
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Response time is less than 500ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(500);
});

pm.test("Response contains id field", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.id).to.exist;
});
```

### Postman to Rest Assured Migration

**Postman Request:**
```json
{
  "method": "POST",
  "url": "https://fakestoreapi.com/carts",
  "body": {
    "userId": 1,
    "products": [{"productId": 1, "quantity": 2}]
  }
}
```

**Rest Assured Equivalent:**
```java
Response response = RestAssured.given()
    .header("Content-Type", "application/json")
    .body("{\"userId\": 1, \"products\": [{\"productId\": 1, \"quantity\": 2}]}")
    .when()
    .post("https://fakestoreapi.com/carts");
```

---

## 🎯 Configuration Management

### config.properties File

Located at: `src/test/resources/config.properties`

```properties
# Browser Configuration
browser=chrome
headless=false
window_size=1920,1080

# URLs
base_url=https://automationexercise.com/
api_base_url=https://fakestoreapi.com

# Timeouts (in seconds)
implicit_wait=10
explicit_wait=15
page_load_timeout=30
api_request_timeout=5000

# Test Data
valid_email=customer@automationpractice.com
valid_password=password123
invalid_email=invalid@test.com
invalid_password=wrongpassword

# Reporting
report_path=reports/
screenshot_path=reports/screenshots/
```

### testdata.json File

Located at: `src/test/resources/testdata.json`

```json
{
  "loginTestData": [
    {
      "email": "customer@automationpractice.com",
      "password": "password123",
      "expectedResult": "success"
    }
  ],
  "productTestData": [
    {
      "productName": "Sleeveless Dress",
      "searchKeyword": "dress"
    }
  ]
}
```

---

## 🧠 Interview Explanations

### 1. **Framework Architecture**

**Q: Explain your framework architecture.**

A: "I've implemented a hybrid automation framework using the Page Object Model (POM) architecture. The framework is organized into:

1. **Base Classes**: BaseTest provides common setup/teardown for all tests
2. **Page Objects**: Separate classes for each UI page (HomePage, LoginPage, etc.)
3. **Utilities**: DriverFactory manages WebDriver lifecycle, WaitUtils handles explicit waits, ScreenshotUtils captures failures
4. **Tests**: UI tests use Selenium WebDriver, API tests use Rest Assured
5. **Configuration**: Centralized properties file for URLs, timeouts, and credentials

This separation of concerns makes the code maintainable, scalable, and reduces duplication."

### 2. **Page Object Model Benefits**

**Q: Why did you choose Page Object Model?**

A: "POM provides several benefits:
- **Maintainability**: Locators are in one place; if UI changes, I update only the page object
- **Readability**: Test code reads like natural language (e.g., `loginPage.login(email, password)`)
- **Reusability**: Page methods can be reused across multiple tests
- **Reduces Duplication**: Common actions are encapsulated in methods
- **Professional Practice**: Industry standard used in major tech companies"

### 3. **Wait Strategy**

**Q: How do you handle timing issues in your tests?**

A: "I use a multi-layered wait strategy:
1. **Implicit Wait**: Set globally in WebDriver for all element interactions (10 seconds)
2. **Explicit Wait**: For specific elements that take longer to load (15 seconds)
3. **Hard Wait**: Used sparingly for known delays (JavaScript execution, animations)
4. **Wait Conditions**: WaitUtils class provides methods like waitForElementToBeClickable(), waitForTextInElement()

This prevents flaky tests while keeping test execution efficient."

### 4. **API Testing Approach**

**Q: How do you approach API testing?**

A: "Following industry best practices:
1. **Design Phase**: Use Postman to manually design and validate APIs
2. **Export Phase**: Export Postman collection for documentation
3. **Automation Phase**: Automate the same APIs using Rest Assured
4. **Validation**: Check status codes, response headers, JSON structure, and business logic
5. **Error Handling**: Test negative scenarios (invalid IDs, wrong data types)
6. **Performance**: Verify response times"

### 5. **CI/CD Integration**

**Q: How do you integrate tests with Jenkins?**

A: "I created a Jenkinsfile with following stages:
1. **Checkout**: Clone code from Git
2. **Build**: Run Maven clean compile
3. **Test**: Execute both UI and API tests in parallel
4. **Report**: Generate Extent Reports and TestNG reports
5. **Archive**: Store artifacts for later analysis

The pipeline supports:
- Parameter selection (which tests to run)
- Headless mode for CI environment
- Parallel test execution for faster feedback
- Email/Slack notifications on failures"

### 6. **Test Data Management**

**Q: How do you manage test data?**

A: "I use a multi-source approach:
1. **Properties File**: Static config (URLs, timeouts, credentials)
2. **JSON File**: Structured test data (login data, product data)
3. **TestDataUtils**: Helper class to read from JSON
4. **Environment Variables**: For sensitive data in CI/CD

Example:
```java
String email = ConfigReader.getValidEmail();
String productName = TestDataUtils.getProductTestData(0, "productName");
```"

### 7. **Error Handling & Reporting**

**Q: How do you handle test failures and generate reports?**

A: "I implemented comprehensive error handling:
1. **Screenshots**: Captured automatically on failure
2. **Extent Reports**: Beautiful HTML reports with screenshots
3. **Logging**: Test actions logged through ReportManager
4. **Assertions**: Meaningful assertions with clear messages
5. **Post Actions**: Screenshots and driver cleanup in @AfterMethod

Reports include:
- Test status (Pass/Fail/Skip)
- Screenshots
- Step logs
- Execution time
- Environment info"

---

## 🔐 Best Practices Implemented

### 1. **Code Organization**
✅ Separated concerns (Page Objects, Tests, Utilities)
✅ DRY principle (Don't Repeat Yourself)
✅ Clear naming conventions
✅ JavaDoc comments for complex methods

### 2. **Test Design**
✅ Single responsibility per test
✅ Meaningful test names
✅ Proper assertions
✅ Both positive and negative scenarios

### 3. **Waits & Synchronization**
✅ Explicit waits for better reliability
✅ Proper wait conditions
✅ No hard waits (except when necessary)

### 4. **Data Management**
✅ Externalized test data
✅ Configuration-driven tests
✅ Test data independence

### 5. **Reporting & Logging**
✅ Detailed test reports
✅ Screenshot capture
✅ Step-by-step logging
✅ Performance metrics

### 6. **Version Control**
✅ Git-friendly structure
✅ .gitignore for build artifacts
✅ Meaningful commit messages

---

## 🚨 Troubleshooting

### Issue: WebDriver Not Found
**Solution**: WebDriverManager automatically downloads ChromeDriver. Ensure internet connection.

### Issue: Tests Fail to Find Elements
**Solution**: 
- Increase explicit wait in `WaitUtils`
- Verify locators match the actual application
- Check if element is in iframe (not handled in current locators)

### Issue: Postman Collection Import Fails
**Solution**: Ensure JSON is valid format. Use Postman's built-in validator.

### Issue: Jenkins Build Fails
**Solution**:
- Check Maven installation
- Verify Java version (11+)
- Review console output for detailed error

---

## 📚 Additional Resources

### Official Documentation
- [Selenium WebDriver Docs](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Rest Assured Docs](https://rest-assured.io/)
- [Maven Official Guide](https://maven.apache.org/guides/)

### Related Tools
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
- [Extent Reports](https://www.extentreports.com/)
- [Postman Learning Center](https://learning.postman.com/)

---

## 🤝 Contributing

Contributions welcome! Follow these steps:
1. Fork the repository
2. Create feature branch: `git checkout -b feature/your-feature`
3. Commit changes: `git commit -am 'Add feature'`
4. Push to branch: `git push origin feature/your-feature`
5. Create Pull Request

---

## 📄 License

This project is licensed under the MIT License - see LICENSE file for details.

---

## 👤 Author

**Your Name**
- GitHub: [@your-username](https://github.com/your-username)
- Email: your-email@example.com
- LinkedIn: [Your Profile](https://linkedin.com/in/your-profile)

---

## 📞 Support

For issues, questions, or suggestions:
1. Create a GitHub Issue
2. Contact: your-email@example.com
3. Check existing documentation

---

**Last Updated**: January 8, 2026
**Framework Version**: 1.0.0
**Status**: ✅ Production Ready

---

## Quick Command Reference

```bash
# Maven Commands
mvn clean test                          # Run all tests
mvn test -Dtest=ui.*                   # Run UI tests only
mvn test -Dtest=api.*                  # Run API tests only
mvn test -Dtest=LoginTest              # Run specific test
mvn surefire-report:report              # Generate Surefire report

# Jenkins Commands
mvn clean compile test                  # Pipeline equivalent
mvn test -Dheadless=true               # Run in headless mode

# Git Commands
git clone <repo-url>                    # Clone repository
git checkout -b feature/branch          # Create new branch
git commit -am "message"                # Commit changes
git push origin branch                  # Push changes

# Report Viewing
open reports/extent-report-*.html       # Open report (macOS)
start reports/extent-report-*.html      # Open report (Windows)
```

---

**Happy Testing! 🎉**

# Testing Guide - How to Run Tests

## 🚀 Overview

This guide explains how to run tests locally, via Maven, and in Jenkins CI/CD.

---

## 📋 Prerequisites

- ✅ Java 11 or higher installed
- ✅ Maven 3.8.1+ installed
- ✅ Chrome browser installed
- ✅ Project imported in Eclipse/IDE

---

## 🔧 LOCAL TESTING

### Option 1: Run in Eclipse (Easiest)

#### Step 1: Open Project
```
1. Eclipse → File → Open Project
2. Select automation-exercise-framework folder
3. Wait for Maven to download dependencies
```

#### Step 2: Run Tests
```
1. Right-click on src/test/runners/testng.xml
2. Select "Run As" → "TestNG Suite"
3. Tests will start executing
```

#### Step 3: View Results
```
1. Go to the Console tab to see execution logs
2. Wait for all tests to complete
3. Open reports/extent-report-[timestamp].html in browser
```

---

### Option 2: Run via Maven Command Line

#### Prerequisites
```
Ensure Maven is added to system PATH
Verify: mvn --version
```

#### Run All Tests
```bash
cd c:\Users\shubh\eclipse-workspace\automation-exercise-framework
mvn clean test
```

#### Run Only UI Tests
```bash
mvn clean test -Dtest=ui.*
```

#### Run Only API Tests
```bash
mvn clean test -Dtest=api.*
```

#### Run Specific Test Class
```bash
mvn clean test -Dtest=ui.LoginTest
```

#### Run Specific Test Method
```bash
mvn clean test -Dtest=ui.LoginTest#testLoginWithValidCredentials
```

#### Run with Headless Mode (CI/CD)
```bash
mvn clean test -Dheadless=true
```

---

## 📊 Test Execution Output

### Console Output During Execution
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running ui.LoginTest
[INFO] Starting login test with valid credentials
✓ Home page loaded successfully
✓ Login page loaded successfully
✓ User successfully logged in
[INFO] Tests run: 4, Failures: 0, Skips: 0, Time elapsed: 45.123 s
[INFO] BUILD SUCCESS
```

### Report Generation
```
[INFO] -------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] -------------------------------------------------------
[INFO] Total time: 2:35.456 s
[INFO] Finished at: 2024-01-08T15:30:45+05:30

Reports generated at: reports/extent-report-2024-01-08_15-30-45.html
```

---

## 📈 Viewing Test Reports

### Extent Report (HTML)

#### Location
```
Reports are generated in: reports/extent-report-[timestamp].html
```

#### How to Open
1. Navigate to `reports/` folder
2. Find latest `extent-report-*.html` file
3. Right-click and open with browser
4. View:
   - Test summary
   - Pass/Fail status
   - Execution time
   - Screenshots
   - Logs
   - Browser/OS info

#### Report Features
- ✅ Visual test status
- ✅ Screenshot attachments
- ✅ Detailed step logs
- ✅ Execution timeline
- ✅ Statistics
- ✅ Environment details

---

## 🐛 Debugging Failed Tests

### Enable Debug Mode

#### Increase Log Output
Edit `src/test/resources/config.properties`:
```properties
log_level=DEBUG
```

#### Add Manual Screenshots
```java
ScreenshotUtils.captureScreenshot(driver, "DebugImage");
```

#### Print Page Source
```java
System.out.println("Page Source:");
System.out.println(driver.getPageSource());
```

#### Print Response
```java
System.out.println("API Response:");
System.out.println(response.getBody().prettyPrint());
```

### Review Test Logs

1. Check Console tab in Eclipse
2. Look for error messages
3. Review timestamps for test sequence
4. Check reports for screenshots

---

## 🔄 Test Execution Flow

### UI Test Execution

```
1. setUp() - Initialize WebDriver (BaseTest)
   ↓
2. Navigate to URL (ConfigReader)
   ↓
3. Create Page Objects (HomePage, LoginPage, etc.)
   ↓
4. Perform Actions (click, sendKeys, etc.)
   ↓
5. Assert Results (Assert.assertEquals, etc.)
   ↓
6. Log Results (ReportManager)
   ↓
7. tearDown() - Close WebDriver, capture screenshots
   ↓
8. Generate Report (Extent Reports)
```

### API Test Execution

```
1. setUp() - Set API base URL (BaseAPITest)
   ↓
2. Build Request (RestAssured.given())
   ↓
3. Send Request (.when().get/post/put/delete())
   ↓
4. Validate Response (.then().assertThat().statusCode())
   ↓
5. Assert Fields (Assert.assertEquals, etc.)
   ↓
6. Log Response (System.out.println())
   ↓
7. Generate Report (TestNG Report)
```

---

## 🚩 Test Status Meanings

| Status | Meaning | Action |
|--------|---------|--------|
| ✅ PASS | Test passed all assertions | No action needed |
| ❌ FAIL | Test failed an assertion | Review error, fix code |
| ⏭️ SKIP | Test was skipped | Check for skip condition |
| 🚨 ERROR | Test had runtime error | Check logs, fix code |

---

## 📊 Common Test Issues & Solutions

### Issue: Element Not Found Exception
```
Error: No such element: {"method":"xpath",...}

Solution:
1. Verify element locator in page object
2. Check if element exists on page
3. Increase wait time in config.properties
4. Use browser developer tools to inspect element
```

### Issue: WebDriver Timeout
```
Error: Timed out after 15 seconds waiting for...

Solution:
1. Element might be inside iframe (not handled)
2. Page taking longer to load (increase timeout)
3. Element might be hidden initially
4. JavaScript not rendering the element
```

### Issue: API Response Error
```
Error: Response status code: 404

Solution:
1. Verify endpoint URL is correct
2. Check API base URL in config
3. Verify authentication/headers if needed
4. Test API in Postman first
```

### Issue: Test Data Not Found
```
Error: Cannot read test data from JSON

Solution:
1. Verify testdata.json exists at correct path
2. Check JSON syntax is valid
3. Verify field names in JSON
4. Use JSON validator online
```

---

## ⚙️ Configuration for Different Environments

### Development Environment
```properties
# config.properties
browser=chrome
headless=false
base_url=https://automationexercise.com/
implicit_wait=10
explicit_wait=15
```

### Staging Environment
```properties
browser=chrome
headless=false
base_url=https://staging.automationexercise.com/
implicit_wait=15
explicit_wait=20
```

### CI/CD Environment (Jenkins)
```properties
browser=chrome
headless=true
base_url=https://automationexercise.com/
implicit_wait=15
explicit_wait=20
```

---

## 🔐 Security Best Practices

### Never Commit Credentials
```
DO NOT add to config.properties:
- Real email addresses
- Real passwords
- API keys
- Authentication tokens
```

### Use Environment Variables
```bash
# In Jenkins, set:
export TEST_EMAIL=your-email
export TEST_PASSWORD=your-password

# In code, read from environment:
String email = System.getenv("TEST_EMAIL");
```

### Use .gitignore
```
# Already configured to ignore:
credentials.json
secrets.properties
*.log
```

---

## 📈 Performance Tips

### Run Tests in Parallel
TestNG automatically runs in parallel if configured:
```xml
<suite name="..." parallel="tests" thread-count="3">
    <!-- Tests run simultaneously -->
</suite>
```

### Run Only Failing Tests
```bash
mvn test -Dtest=ui.LoginTest#testLoginWithValidCredentials
```

### Skip Screenshots (Faster)
Comment out in BaseTest.tearDown():
```java
// ScreenshotUtils.captureScreenshot(driver, "AfterTest");
```

### Use Headless Mode
```bash
mvn test -Dheadless=true
```

---

## 🔗 Jenkins Integration

### Run Tests via Jenkins

#### Setup (One time)
1. Install Jenkins plugins:
   - Git
   - Maven Integration
   - TestNG Results
   - Email Extension

2. Create Jenkins Job:
   - New Item → Pipeline
   - Name: automation-exercise-tests
   - Pipeline → Pipeline script from SCM
   - Git repository URL
   - Branch: main
   - Script path: Jenkinsfile

#### Execute
1. Click **Build Now**
2. Monitor **Console Output**
3. View **Test Results**
4. Download **Artifacts**

#### View Reports in Jenkins
1. Go to build
2. Click **Extent Report**
3. View test results

---

## 📋 Test Checklist Before Committing

- [ ] All tests pass locally
- [ ] No hardcoded values used
- [ ] Configuration externalized
- [ ] Screenshots captured on failures
- [ ] Logging implemented
- [ ] Assertions meaningful
- [ ] Code properly formatted
- [ ] No sensitive data
- [ ] Page objects updated
- [ ] testng.xml updated

---

## 🎓 Test Organization Best Practices

### Group Related Tests
```java
@Test(groups = "login")
public void testLogin() { }

@Test(groups = "products")
public void testProductSearch() { }
```

### Priority-Based Execution
```java
@Test(priority = 1)
public void testFirst() { }

@Test(priority = 2)
public void testSecond() { }
```

### Dependency Between Tests
```java
@Test(dependsOnMethods = {"testLogin"})
public void testLogout() { }
```

---

## 📞 Troubleshooting Commands

### Clear Maven Cache
```bash
mvn clean
rm -rf ~/.m2/repository/
mvn install
```

### Check Java Version
```bash
java -version
# Should be 11+
```

### Check Maven Version
```bash
mvn --version
# Should be 3.8.1+
```

### Verify Project Structure
```bash
cd automation-exercise-framework
ls -la src/
ls -la pom.xml
```

### Run with Verbose Logging
```bash
mvn test -X -e
```

---

## 🎯 Test Execution Summary

### Quick Run
```bash
# 30 seconds
mvn clean test -Dtest=api.GetProductsTest
```

### Full Suite
```bash
# 5-10 minutes (depending on network)
mvn clean test
```

### With Reports
```bash
# 5-10 minutes
mvn clean test
open reports/extent-report-*.html
```

---

## ✅ Successful Test Execution Indicators

1. **Console Output**
   ```
   [INFO] BUILD SUCCESS
   ```

2. **Report Generated**
   ```
   Reports generated at: reports/extent-report-[timestamp].html
   ```

3. **Test Count**
   ```
   Tests run: 38, Failures: 0, Skips: 0
   ```

4. **All Tests Pass**
   ```
   ✓ test1 - PASSED
   ✓ test2 - PASSED
   ```

---

## 🚀 Next Steps After Successful Test Run

1. Review the Extent Report
2. Verify all tests passed
3. Check for any warnings in logs
4. Commit code to Git
5. Push to GitHub
6. Trigger Jenkins build
7. Monitor Jenkins console
8. Share report with team

---

## 💡 Tips for Effective Testing

1. **Always use Page Objects** - Don't access elements directly in tests
2. **Meaningful Assertions** - Use clear assertion messages
3. **Proper Waits** - Use explicit waits, avoid hard waits
4. **Data Externalization** - Use config.properties, not hardcoded values
5. **Screenshot on Failure** - Helps in debugging
6. **Log Every Step** - Makes reports readable
7. **Run Full Suite** - Before committing code
8. **Review Reports** - Always check test reports

---

## 📚 Reference Documents

1. **README.md** - Comprehensive documentation
2. **QUICK_REFERENCE.md** - Quick command reference
3. **PROJECT_SUMMARY.md** - Project overview
4. **This File** - Testing guide

---

**Happy Testing! 🎉**

For more information, see README.md

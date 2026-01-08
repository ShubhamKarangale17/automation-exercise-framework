package main.java.utils;

import org.testng.Assert;
import io.restassured.response.Response;

/**
 * AssertionUtils - Custom assertion methods for cleaner test code
 */
public class AssertionUtils {

    /**
     * Assert that actual value equals expected value
     *
     * @param actual - Actual value
     * @param expected - Expected value
     * @param message - Assertion message
     */
    public static void assertEqual(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    /**
     * Assert that actual value is not equal to expected value
     *
     * @param actual - Actual value
     * @param expected - Expected value
     * @param message - Assertion message
     */
    public static void assertNotEqual(Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }

    /**
     * Assert that condition is true
     *
     * @param condition - Condition to check
     * @param message - Assertion message
     */
    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    /**
     * Assert that condition is false
     *
     * @param condition - Condition to check
     * @param message - Assertion message
     */
    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    /**
     * Assert that object is not null
     *
     * @param object - Object to check
     * @param message - Assertion message
     */
    public static void assertNotNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }

    /**
     * Assert that object is null
     *
     * @param object - Object to check
     * @param message - Assertion message
     */
    public static void assertNull(Object object, String message) {
        Assert.assertNull(object, message);
    }

    /**
     * Assert that string contains substring
     *
     * @param actual - Actual string
     * @param substring - Substring to find
     * @param message - Assertion message
     */
    public static void assertContains(String actual, String substring, String message) {
        Assert.assertTrue(actual.contains(substring), message);
    }

    /**
     * Assert API response status code
     *
     * @param response - Response object
     * @param expectedStatusCode - Expected status code
     * @param message - Assertion message
     */
    public static void assertStatusCode(Response response, int expectedStatusCode, String message) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, message);
    }

    /**
     * Assert API response is successful (200-299)
     *
     * @param response - Response object
     * @param message - Assertion message
     */
    public static void assertSuccessResponse(Response response, String message) {
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode >= 200 && statusCode < 300,
                message + " (Received: " + statusCode + ")");
    }

    /**
     * Assert that response body is not empty
     *
     * @param response - Response object
     * @param message - Assertion message
     */
    public static void assertResponseNotEmpty(Response response, String message) {
        String body = response.getBody().asString();
        Assert.assertFalse(body.isEmpty(), message);
    }

    /**
     * Assert that response contains JSON field
     *
     * @param response - Response object
     * @param jsonPath - JSON path to the field
     * @param message - Assertion message
     */
    public static void assertResponseContainsField(Response response, String jsonPath, String message) {
        try {
            Object value = response.jsonPath().get(jsonPath);
            Assert.assertNotNull(value, message);
        } catch (Exception e) {
            Assert.fail(message + " - " + e.getMessage());
        }
    }

    /**
     * Assert that element is displayed
     *
     * @param isDisplayed - Is element displayed
     * @param message - Assertion message
     */
    public static void assertElementDisplayed(boolean isDisplayed, String message) {
        Assert.assertTrue(isDisplayed, message);
    }

    /**
     * Assert that element is not displayed
     *
     * @param isDisplayed - Is element displayed
     * @param message - Assertion message
     */
    public static void assertElementNotDisplayed(boolean isDisplayed, String message) {
        Assert.assertFalse(isDisplayed, message);
    }

    /**
     * Assert that value is greater than threshold
     *
     * @param actual - Actual value
     * @param threshold - Threshold value
     * @param message - Assertion message
     */
    public static void assertGreaterThan(long actual, long threshold, String message) {
        Assert.assertTrue(actual > threshold, message + " (Expected > " + threshold + ", Got: " + actual + ")");
    }

    /**
     * Assert that value is less than threshold
     *
     * @param actual - Actual value
     * @param threshold - Threshold value
     * @param message - Assertion message
     */
    public static void assertLessThan(long actual, long threshold, String message) {
        Assert.assertTrue(actual < threshold, message + " (Expected < " + threshold + ", Got: " + actual + ")");
    }
}

package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import config.ConfigReader;

/**
 * BaseAPITest - Base class for all API test classes
 * Provides common setup and utility methods for API testing
 */
public class BaseAPITest {

    @BeforeClass
    public void setUpAPI() {
        // Set base URI for all API tests
        RestAssured.baseURI = ConfigReader.getApiBaseUrl();
        
        // Optional: Set authentication, headers, etc.
        System.out.println("API Base URL set to: " + ConfigReader.getApiBaseUrl());
    }

    /**
     * Get default timeout for API requests
     *
     * @return Timeout in milliseconds
     */
    protected int getApiTimeout() {
        return ConfigReader.getApiTimeout();
    }

    /**
     * Get base API URL
     *
     * @return API base URL
     */
    protected String getBaseApiUrl() {
        return ConfigReader.getApiBaseUrl();
    }

    /**
     * Build standard headers for API requests
     *
     * @return Headers map
     */
    protected String[] buildStandardHeaders() {
        return new String[]{
                "Content-Type", "application/json",
                "Accept", "application/json"
        };
    }

    /**
     * Log API response for debugging
     *
     * @param response - Response object
     */
    protected void logResponse(Response response) {
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Time: " + response.getTime() + "ms");
        System.out.println("Content-Type: " + response.getHeader("Content-Type"));
        System.out.println("Response Body:\n" + response.getBody().prettyPrint());
    }

    /**
     * Verify response status code is success (200-299)
     *
     * @param response - Response object
     * @return true if status code indicates success
     */
    protected boolean isSuccessResponse(Response response) {
        int statusCode = response.getStatusCode();
        return statusCode >= 200 && statusCode < 300;
    }

    /**
     * Verify response status code is client error (400-499)
     *
     * @param response - Response object
     * @return true if status code indicates client error
     */
    protected boolean isClientErrorResponse(Response response) {
        int statusCode = response.getStatusCode();
        return statusCode >= 400 && statusCode < 500;
    }

    /**
     * Verify response status code is server error (500-599)
     *
     * @param response - Response object
     * @return true if status code indicates server error
     */
    protected boolean isServerErrorResponse(Response response) {
        int statusCode = response.getStatusCode();
        return statusCode >= 500 && statusCode < 600;
    }
}

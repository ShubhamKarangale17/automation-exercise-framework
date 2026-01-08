package test.java.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import config.ConfigReader;

/**
 * GetProductsTest - API test cases for GET operations
 */
public class GetProductsTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigReader.getApiBaseUrl();
    }

    /**
     * Test GET all products from API
     */
    @Test(description = "Verify GET all products returns 200 status code")
    public void testGetAllProducts() {
        System.out.println("Starting test: Get all products");

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .when()
                .get("/products")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        System.out.println("✓ GET /products returned 200 OK");

        // Verify response is not empty
        String responseBody = response.getBody().asString();
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");
        System.out.println("✓ Response body is not empty");

        // Verify response contains products array
        Assert.assertTrue(responseBody.contains("id"), "Response does not contain product IDs");
        System.out.println("✓ Response contains expected fields");
    }

    /**
     * Test GET product by specific ID
     */
    @Test(description = "Verify GET product by ID returns 200 status code")
    public void testGetProductById() {
        System.out.println("Starting test: Get product by ID");

        int productId = 1;
        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .pathParam("id", productId)
                .when()
                .get("/products/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        System.out.println("✓ GET /products/" + productId + " returned 200 OK");

        // Verify response contains product details
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("\"id\":" + productId), "Product ID not found in response");
        System.out.println("✓ Response contains product with ID: " + productId);
    }

    /**
     * Test GET product with invalid ID
     */
    @Test(description = "Verify GET product with invalid ID returns appropriate status")
    public void testGetProductWithInvalidId() {
        System.out.println("Starting test: Get product with invalid ID");

        int invalidProductId = 99999;
        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .pathParam("id", invalidProductId)
                .when()
                .get("/products/{id}");

        System.out.println("Response status code: " + response.getStatusCode());
        Assert.assertTrue(response.getStatusCode() == 404 || response.getStatusCode() == 500,
                "Unexpected status code for invalid product ID");
        System.out.println("✓ Invalid product ID handling verified");
    }

    /**
     * Test GET products with limit parameter
     */
    @Test(description = "Verify GET products with limit parameter")
    public void testGetProductsWithLimit() {
        System.out.println("Starting test: Get products with limit");

        int limit = 5;
        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .queryParam("limit", limit)
                .when()
                .get("/products")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        System.out.println("✓ GET /products?limit=" + limit + " returned 200 OK");

        String responseBody = response.getBody().asString();
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");
        System.out.println("✓ Response received with limit parameter");
    }

    /**
     * Test GET products response headers
     */
    @Test(description = "Verify GET products response headers")
    public void testGetProductsResponseHeaders() {
        System.out.println("Starting test: Get products response headers");

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .when()
                .get("/products")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        // Verify content type
        String contentType = response.getHeader("Content-Type");
        System.out.println("Content-Type: " + contentType);
        Assert.assertNotNull(contentType, "Content-Type header is null");
        Assert.assertTrue(contentType.contains("application/json"), "Content-Type is not JSON");
        System.out.println("✓ Content-Type header verified");
    }

    /**
     * Test response time for GET products
     */
    @Test(description = "Verify GET products response time is acceptable")
    public void testGetProductsResponseTime() {
        System.out.println("Starting test: Get products response time");

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .when()
                .get("/products")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        long responseTime = response.getTime();
        System.out.println("Response time: " + responseTime + "ms");

        // Assume response time should be less than 5 seconds
        Assert.assertTrue(responseTime < 5000, "Response time is too high: " + responseTime + "ms");
        System.out.println("✓ Response time is within acceptable range");
    }
}

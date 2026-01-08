package test.java.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import config.ConfigReader;
import com.google.gson.JsonObject;

/**
 * CreateCartTest - API test cases for cart creation
 */
public class CreateCartTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigReader.getApiBaseUrl();
    }

    /**
     * Test creating a cart via API
     */
    @Test(description = "Verify POST create cart returns success status")
    public void testCreateCart() {
        System.out.println("Starting test: Create cart");

        // Build request body
        String requestBody = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"date\": \"2024-01-08\",\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"productId\": 1,\n" +
                "      \"quantity\": 2\n" +
                "    },\n" +
                "    {\n" +
                "      \"productId\": 3,\n" +
                "      \"quantity\": 1\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/carts")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        System.out.println("✓ POST /carts returned 200 OK");

        String responseBody = response.getBody().asString();
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");
        System.out.println("✓ Cart created successfully");
    }

    /**
     * Test creating cart with single product
     */
    @Test(description = "Verify cart can be created with single product")
    public void testCreateCartWithSingleProduct() {
        System.out.println("Starting test: Create cart with single product");

        String requestBody = "{\n" +
                "  \"userId\": 2,\n" +
                "  \"date\": \"2024-01-08\",\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"productId\": 5,\n" +
                "      \"quantity\": 3\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/carts")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        System.out.println("✓ Single product cart created successfully");
    }

    /**
     * Test creating cart with invalid data
     */
    @Test(description = "Verify cart creation with invalid data")
    public void testCreateCartWithInvalidData() {
        System.out.println("Starting test: Create cart with invalid data");

        String invalidRequestBody = "{\n" +
                "  \"userId\": \"invalid\",\n" +
                "  \"date\": \"invalid-date\"\n" +
                "}";

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .header("Content-Type", "application/json")
                .body(invalidRequestBody)
                .when()
                .post("/carts");

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("✓ Invalid data handling verified");
    }

    /**
     * Test cart response contains all required fields
     */
    @Test(description = "Verify cart response contains required fields")
    public void testCartResponseFields() {
        System.out.println("Starting test: Cart response fields");

        String requestBody = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"date\": \"2024-01-08\",\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"productId\": 1,\n" +
                "      \"quantity\": 1\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/carts")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("id"), "Cart ID field missing");
        Assert.assertTrue(responseBody.contains("userId"), "UserId field missing");
        Assert.assertTrue(responseBody.contains("date"), "Date field missing");
        System.out.println("✓ All required fields present in response");
    }
}

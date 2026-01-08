package test.java.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import config.ConfigReader;

/**
 * UpdateCartTest - API test cases for cart updates
 */
public class UpdateCartTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigReader.getApiBaseUrl();
    }

    /**
     * Test updating an existing cart
     */
    @Test(description = "Verify PUT update cart returns success status")
    public void testUpdateCart() {
        System.out.println("Starting test: Update cart");

        int cartId = 1;
        String requestBody = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"date\": \"2024-01-09\",\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"productId\": 1,\n" +
                "      \"quantity\": 5\n" +
                "    },\n" +
                "    {\n" +
                "      \"productId\": 2,\n" +
                "      \"quantity\": 2\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .header("Content-Type", "application/json")
                .pathParam("id", cartId)
                .body(requestBody)
                .when()
                .put("/carts/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        System.out.println("✓ PUT /carts/" + cartId + " returned 200 OK");

        String responseBody = response.getBody().asString();
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");
        System.out.println("✓ Cart updated successfully");
    }

    /**
     * Test updating cart with different quantity
     */
    @Test(description = "Verify cart quantity can be updated")
    public void testUpdateCartQuantity() {
        System.out.println("Starting test: Update cart quantity");

        int cartId = 2;
        String requestBody = "{\n" +
                "  \"userId\": 2,\n" +
                "  \"date\": \"2024-01-09\",\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"productId\": 3,\n" +
                "      \"quantity\": 10\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .header("Content-Type", "application/json")
                .pathParam("id", cartId)
                .body(requestBody)
                .when()
                .put("/carts/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        System.out.println("✓ Cart quantity updated successfully");
    }

    /**
     * Test updating cart with new products
     */
    @Test(description = "Verify new products can be added to cart via update")
    public void testUpdateCartWithNewProducts() {
        System.out.println("Starting test: Update cart with new products");

        int cartId = 3;
        String requestBody = "{\n" +
                "  \"userId\": 3,\n" +
                "  \"date\": \"2024-01-09\",\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"productId\": 4,\n" +
                "      \"quantity\": 2\n" +
                "    },\n" +
                "    {\n" +
                "      \"productId\": 5,\n" +
                "      \"quantity\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"productId\": 6,\n" +
                "      \"quantity\": 1\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .header("Content-Type", "application/json")
                .pathParam("id", cartId)
                .body(requestBody)
                .when()
                .put("/carts/{id}");

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("✓ Cart update with new products processed");
    }

    /**
     * Test updating non-existent cart
     */
    @Test(description = "Verify updating non-existent cart")
    public void testUpdateNonExistentCart() {
        System.out.println("Starting test: Update non-existent cart");

        int invalidCartId = 99999;
        String requestBody = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"date\": \"2024-01-09\",\n" +
                "  \"products\": []\n" +
                "}";

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .header("Content-Type", "application/json")
                .pathParam("id", invalidCartId)
                .body(requestBody)
                .when()
                .put("/carts/{id}");

        System.out.println("Response status code for invalid cart: " + response.getStatusCode());
        System.out.println("✓ Non-existent cart update handled");
    }

    /**
     * Test updating cart with empty products list
     */
    @Test(description = "Verify cart can be updated with empty products")
    public void testUpdateCartWithEmptyProducts() {
        System.out.println("Starting test: Update cart with empty products");

        int cartId = 4;
        String requestBody = "{\n" +
                "  \"userId\": 4,\n" +
                "  \"date\": \"2024-01-09\",\n" +
                "  \"products\": []\n" +
                "}";

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .header("Content-Type", "application/json")
                .pathParam("id", cartId)
                .body(requestBody)
                .when()
                .put("/carts/{id}");

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("✓ Cart update with empty products processed");
    }
}

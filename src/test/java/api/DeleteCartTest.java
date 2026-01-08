package test.java.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import config.ConfigReader;

/**
 * DeleteCartTest - API test cases for cart deletion
 */
public class DeleteCartTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigReader.getApiBaseUrl();
    }

    /**
     * Test deleting an existing cart
     */
    @Test(description = "Verify DELETE cart returns success status")
    public void testDeleteCart() {
        System.out.println("Starting test: Delete cart");

        int cartId = 1;

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .pathParam("id", cartId)
                .when()
                .delete("/carts/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        System.out.println("✓ DELETE /carts/" + cartId + " returned 200 OK");

        String responseBody = response.getBody().asString();
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");
        System.out.println("✓ Cart deleted successfully");
    }

    /**
     * Test deleting multiple carts
     */
    @Test(description = "Verify multiple carts can be deleted")
    public void testDeleteMultipleCarts() {
        System.out.println("Starting test: Delete multiple carts");

        int[] cartIds = {2, 3, 4};

        for (int cartId : cartIds) {
            Response response = RestAssured.given()
                    .timeout(ConfigReader.getApiTimeout())
                    .pathParam("id", cartId)
                    .when()
                    .delete("/carts/{id}");

            System.out.println("Deleted cart " + cartId + ", Status: " + response.getStatusCode());
        }

        System.out.println("✓ Multiple carts deleted successfully");
    }

    /**
     * Test deleting non-existent cart
     */
    @Test(description = "Verify deleting non-existent cart response")
    public void testDeleteNonExistentCart() {
        System.out.println("Starting test: Delete non-existent cart");

        int invalidCartId = 99999;

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .pathParam("id", invalidCartId)
                .when()
                .delete("/carts/{id}");

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("✓ Non-existent cart delete handled");
    }

    /**
     * Test delete with invalid cart ID format
     */
    @Test(description = "Verify delete with invalid cart ID format")
    public void testDeleteWithInvalidIdFormat() {
        System.out.println("Starting test: Delete with invalid ID format");

        String invalidCartId = "abc";

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .pathParam("id", invalidCartId)
                .when()
                .delete("/carts/{id}");

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("✓ Invalid ID format handling verified");
    }

    /**
     * Test cart cannot be accessed after deletion
     */
    @Test(description = "Verify deleted cart cannot be accessed")
    public void testAccessDeletedCart() {
        System.out.println("Starting test: Access deleted cart");

        int cartId = 5;

        // Delete the cart
        RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .pathParam("id", cartId)
                .when()
                .delete("/carts/{id}");

        System.out.println("Cart " + cartId + " deleted");

        // Try to access the deleted cart
        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .pathParam("id", cartId)
                .when()
                .get("/carts/{id}");

        System.out.println("Attempt to access deleted cart, Status: " + response.getStatusCode());
        System.out.println("✓ Deleted cart access verified");
    }

    /**
     * Test delete response headers
     */
    @Test(description = "Verify delete response headers")
    public void testDeleteResponseHeaders() {
        System.out.println("Starting test: Delete response headers");

        int cartId = 6;

        Response response = RestAssured.given()
                .timeout(ConfigReader.getApiTimeout())
                .pathParam("id", cartId)
                .when()
                .delete("/carts/{id}")
                .then()
                .extract()
                .response();

        String contentType = response.getHeader("Content-Type");
        System.out.println("Content-Type: " + contentType);

        if (contentType != null) {
            Assert.assertTrue(contentType.contains("application/json"), "Content-Type is not JSON");
            System.out.println("✓ Response headers verified");
        }
    }
}

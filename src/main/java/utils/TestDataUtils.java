package main.java.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;

/**
 * TestDataUtils - Provides utilities to read test data from JSON file
 */
public class TestDataUtils {

    /**
     * Get test data from JSON file
     *
     * @param jsonFilePath - Path to JSON file
     * @param dataKey - Key for the test data array
     * @param index - Index of the data object
     * @param fieldKey - Key of the field to retrieve
     * @return Field value as string
     */
    public static String getTestDataFromJson(String jsonFilePath, String dataKey, int index, String fieldKey) {
        try {
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(jsonFilePath));
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray dataArray = jsonObject.getAsJsonArray(dataKey);

            if (index < dataArray.size()) {
                JsonObject dataObject = dataArray.get(index).getAsJsonObject();
                return dataObject.get(fieldKey).getAsString();
            }
        } catch (Exception e) {
            System.err.println("Error reading test data from JSON: " + e.getMessage());
        }
        return null;
    }

    /**
     * Get login test data
     *
     * @param index - Index of the login data
     * @param fieldKey - Field to retrieve (email, password, expectedResult)
     * @return Field value
     */
    public static String getLoginTestData(int index, String fieldKey) {
        return getTestDataFromJson("src/test/resources/testdata.json", "loginTestData", index, fieldKey);
    }

    /**
     * Get product test data
     *
     * @param index - Index of the product data
     * @param fieldKey - Field to retrieve
     * @return Field value
     */
    public static String getProductTestData(int index, String fieldKey) {
        return getTestDataFromJson("src/test/resources/testdata.json", "productTestData", index, fieldKey);
    }

    /**
     * Get cart test data
     *
     * @param index - Index of the cart data
     * @param fieldKey - Field to retrieve
     * @return Field value
     */
    public static String getCartTestData(int index, String fieldKey) {
        return getTestDataFromJson("src/test/resources/testdata.json", "cartTestData", index, fieldKey);
    }
}

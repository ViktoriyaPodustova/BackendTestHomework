package Homework3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ClassifyCuisine extends AbstractTest {
    @Test
    void verifyingStatusCodePostTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Fresh Cherry Scones")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    void verifyingSLAPostTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language","de")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Low Carb Brunch Burger")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .time(lessThan(2000L));
    }
    @Test
    void verifyingContentTypePostTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language","de")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Sushi")
                .formParam("ingredientList", "rice")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .header("Content-Type", "application/json");

    }
    @Test
    void verifyingConfidenceDataPostTest (){
        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language","en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Chicken Enchilada Casserole")
                .formParam("ingredientList", "Chicken")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response.get("confidence"), equalTo(0.95F));
    }
    @Test
    void verifyingStatusLinePostTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Gujarati Dry Mung Bean Curry")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusLine("HTTP/1.1 200 OK");

    }
}
package Homework3;

import Response.ResponseForClassifyCuisine;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class ClassifyCuisine extends AbstractTest {
    @Test
    void verifyingStatusCodePostTest() {
        given()
                .spec(getRequestSpecification())
                .formParam("title", "Fresh Cherry Scones")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void verifyingSLAPostTest(){
        given()
                .spec(getRequestSpecification())
                .queryParam("language","de")
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
                .spec(getRequestSpecification())
                .queryParam("language","de")
                .formParam("title", "Sushi")
                .formParam("ingredientList", "rice")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(responseSpecification);

    }
    @Test
    void verifyingConfidenceDataPostTest (){
        ResponseForClassifyCuisine responseForClassifyCuisine = given()
                .spec(getRequestSpecification())
                .when()
                .queryParam("language","en")
                .formParam("title", "Chicken Enchilada Casserole")
                .formParam("ingredientList", "Chicken")
                .post(getBaseUrl() + "recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(ResponseForClassifyCuisine.class);
        assertThat(responseForClassifyCuisine.getConfidence(), equalTo(0.95));
    }
    @Test
    void verifyingStatusLinePostTest(){
        given()
                .spec(getRequestSpecification())
                .formParam("title", "Gujarati Dry Mung Bean Curry")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusLine("HTTP/1.1 200 OK");

    }
}
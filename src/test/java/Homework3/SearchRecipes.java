package Homework3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class SearchRecipes extends AbstractTest{
@Test
    void getVerifyingStatusCode(){
    given()
            .queryParam("apiKey", getApiKey())
            .queryParam("excludeCuisine", "Korean")
            .queryParam("maxCalories", 200)
            .when()
            .get(getBaseUrl()+"recipes/complexSearch")
            .then()
            .assertThat()
            .statusCode(200);

}

@Test
    void getVerifyingSLA(){
    given()
            .queryParam("apiKey", getApiKey())
            .queryParam("sort", "price")
            .when()
            .get(getBaseUrl()+"recipes/complexSearch")
            .then()
            .assertThat()
            .time(lessThan(6000L));
}

@Test
    void getVerifyingContentType(){
    given()
            .queryParam("apiKey", getApiKey())
            .queryParam("titleMatch", "zucchini")
            .queryParam("addRecipeInformation", "true")
            .when()
            .get(getBaseUrl()+"recipes/complexSearch")
            .then()
            .assertThat()
            .header("Content-Type", "application/json");

}
@Test
    void getVerifyingNumberData (){
    JsonPath response = given()
        .queryParam("apiKey", getApiKey())
        .queryParam("minVitaminD", 10)
        .queryParam("number", 5)
        .when()
        .get(getBaseUrl()+"recipes/complexSearch")
            .body()
            .jsonPath();
    assertThat(response.get("number"), equalTo(5));
}

    @Test
    void getVerifyingTotalResultsData (){
        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("minProtein", 25)
                .queryParam("equipment", "blender")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("totalResults"), equalTo(41));
    }

}

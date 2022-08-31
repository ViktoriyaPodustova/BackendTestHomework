package Homework3;

import Response.ResponseForClassifyCuisine;
import Response.ResponseForSearchRecipes;
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
    ResponseForSearchRecipes responseForSearchRecipes = given()
            .when()
        .queryParam("apiKey", getApiKey())
        .queryParam("minVitaminD", 10)
        .queryParam("number", 5)
        .get(getBaseUrl()+"recipes/complexSearch")
            .then()
            .extract()
            .response()
            .body()
            .as(ResponseForSearchRecipes.class);
    assertThat(responseForSearchRecipes.getNumber(), equalTo(5));
}

    @Test
    void getVerifyingTotalResultsData (){
        ResponseForSearchRecipes responseForSearchRecipes  = given()
                .when()
                .queryParam("apiKey", getApiKey())
                .queryParam("minProtein", 25)
                .queryParam("equipment", "blender")
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .extract()
                .response()
                .body()
                .as(ResponseForSearchRecipes.class);
        assertThat(responseForSearchRecipes.getTotalResults(), equalTo(41));
    }

}

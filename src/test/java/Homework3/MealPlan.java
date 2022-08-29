package Homework3;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class MealPlan extends AbstractTest{
    @Test
    void addMealTest() {
        String id = given()
                .queryParam("hash", "3153d5e42b618eb4aeb12cd6691fd33ff9f6aeb1")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/vika0/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "3153d5e42b618eb4aeb12cd6691fd33ff9f6aeb1")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/vika0/items/" + id)
                .then()
                .statusCode(200);
    }
}

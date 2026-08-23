package userManagement;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getUserNew {
    public static void main(String[] args) {
        Response response = RestAssured
                .given()
                .baseUri("https://reqres.in")
                .header("x-api-key", "free_user_3HdqfcVy39J3k1hVHfpaNPTFYhY") // custom header
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(202) // expected status
                .extract()
                .response();

        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());
    }
}

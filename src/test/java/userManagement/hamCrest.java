package userManagement;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class hamCrest {
    
@Test
public void Test1(){

    RestAssured.baseURI= "https://jsonplaceholder.typicode.com";

    Response rep= given()
            .when()
            .get("/posts")
            .then().extract().response();

         // Use Hamcrest to check that the response body contains specific items

        assertThat(rep.jsonPath().getList("title"), hasItems("qui est esse","nesciunt quas odio"));

}




}





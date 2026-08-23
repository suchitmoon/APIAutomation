package userManagement;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;



public class practic {

    @Test
    public void getRecords() {


        Response response = given()
                .when()
                .get("https://reqres.in/api")
                .then()
                .extract().response();

        System.out.println("Response Body: " + response.getBody().asString());

    }

    @Test
    public void getUsersList() {
        given()
                .header("x-api-key", "free_user_3HdqfcVy39J3k1hVHfpaNPTFYhY")
                .when()
                .get("https://reqres.in/api/users?page=1")
                .then()
                .statusCode(200)
                .body("data", not(empty()))
                .body("page", equalTo(1));
    }

    @Test
    public void getApiInfo() {
        given()
                .when()
                .get("https://reqres.in/api")
                .then()
                .statusCode(200)
                .body("name", equalTo("ReqRes API"))
                .body("endpoints.free", equalTo("/api/users"));
    }

        // Self made

        @Test
        public void selfMadeTest() {
            
            RestAssured.baseURI =  "https://fake-json-api.mock.beeceptor.com";

            given()
            .when()
            .get("/companies")
            .then()
            .statusCode(200)
            .body("[1].zip", equalTo("48359"));


        }   

        @Test
        public void  test1(){

            given()
            .when()
            .get("https://jsonplaceholder.typicode.com/todos/1")
            .then()
            .statusCode(200)
            .body("id",equalTo(1))
            .body("userId",equalTo(1))
            .body("title",equalTo("delectus aut autem"))
            .body("completed",equalTo(false));
 

        }

        @Test
        public void test2(){

            Response response = given()
                                   .header("x-api-key","free_user_3HdqfcVy39J3k1hVHfpaNPTFYhY")
                                  .when()
                                  .get("https://reqres.in/api/collections/products/records?project_id=44201")
                                 .then()
                                 .extract().response();

                                 System.out.println(response.asString());
            
        }


            @Test
            public void test3(){

                    RestAssured.baseURI ="https://reqres.in";

                    given()
                    .header("x-api-key","free_user_3HdqfcVy39J3k1hVHfpaNPTFYhY")
                    .when()
                    .get("/api/collections/products/records?project_id=44201")
                    .then()
                    .statusCode(200)
                    .body(not(isEmptyString()))
                    .body("data[0].id",equalTo("bd7e32a1-0732-446d-baa5-f8a3d2f51164"))
                    .body("data[1].id",equalTo("ee39c7ab-560e-4501-a15b-b0fbefa69921"))
                    .body("data[2].created_by",equalTo(137791))
                    .body("data[0].data.name",equalTo("Wireless Headphones"));



            }



}

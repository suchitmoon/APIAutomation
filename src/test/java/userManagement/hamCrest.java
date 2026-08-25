package userManagement;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;

import core.StatusCode;

import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.assertEquals;

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

    @Test
    public void test2(){

        Response response = given()
        .when()
        .get("https://jsonplaceholder.typicode.com/comments")
        .then()
        .statusCode(200)
        .extract()
        .response();

            assertThat(response.jsonPath().getList(""), hasSize(500));

    }


    @Test
    public void Test3(){

            Response response = given()
            .when()
            .get("https://jsonplaceholder.typicode.com/comments?postId=1")
            .then()
            .extract()
            .response();

            List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com", "Nikita@garfield.biz","Lew@alysha.tv","Hayden@althea.biz");

            assertThat(response.jsonPath().getList("email"),contains(expectedEmails.toArray(new String[0])));


    }


    @Test
    public void Test4(){

            Response response = given()
            .header("x-api-key","free_user_3HdqfcVy39J3k1hVHfpaNPTFYhY")
            .when()
            .get("https://reqres.in/api/users")
            .then()
            .statusCode(200)
            .extract()
            .response();

            response.then().body("data[0].id",equalTo(1));
             response.then().body("data[4].id", is(5));
        response.then().body("data[1].email", is("janet.weaver@reqres.in"));
        response.then().body("data[1].first_name", is("Janet"));
        response.then().body("data[1].last_name", is("Weaver"));
        response.then().body("data[2].avatar", is("https://reqres.in/img/faces/3-image.jpg"));

    }


    @Test
    public void Test5(){

            Response response = given()
            .queryParam("page",2)
            .when()
            .get("https://reqres.in/api/users");

         int actualStatusCode = response.statusCode();
         assertEquals(actualStatusCode,200);

    }

    @Test
    public void Test6(){

            Response response = given()
            .queryParam("page",2)
            .queryParam("per_page",5)
            .queryParam("dfdf",45)
            .when()
            .get("https://reqres.in/api/users")
           .then()
            .statusCode(StatusCode.SUCCESS.code).extract().response();


}
}





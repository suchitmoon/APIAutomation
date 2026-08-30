package userManagement;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class headers {
    

        @Test
        public void test1(){

           Response response = given()
           .when()
           .get("https://reqres.in/api/users?page=2")
           .then()
           .extract().response();
   
                Headers headers =response.getHeaders();

                for(Header h: headers){
                System.out.println(h.getName() + " : "+ h.getValue());
              }
               
              
                for(Header h: headers){
                    if( h.getName().contains("Server") ){
              
                    System.out.println(h.getName() + " : "+ h.getValue());

                    assertEquals(h.getValue(), "cloudflare");
              }
                }
   
        }

}

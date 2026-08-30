package userManagement;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import static org.hamcrest.Matchers.*;

import org.json.simple.parser.ParseException;
import org.hamcrest.Description;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.JsonReader;
import utils.PropertyReader;

public class auth {




    @Test( description = "Basic Auth Example" )
    public void Test1(){

            Response rep = given()
                            .auth()
                            .basic("postman", "password")
                            .when()
                            .get("https://postman-echo.com/basic-auth"); 


                            int statusCode = rep.getStatusCode();
                           
                            assertEquals(statusCode, 200);
                                                        
                            System.out.println("Response Body : "+ rep.getBody().asString());
    }
    
    @Test(description = "Digest Auth")
    public void atest2(){
                    Response rep = given()
                                .auth()
                                .digest("postman", "password")
                                .when()
                                 .get("https://postman-echo.com/digest-auth"); 

                    int statusCode =rep.getStatusCode();
                    assertEquals(statusCode,200);
                    System.out.println("Repsponse Body : "+rep.getBody().asString());


    }


    @Test(description="validate 204 for Delete user",groups= {"RegressionSuite","B_User"})
public void verifyStatusCodeDelete() {


  Response resp = given().delete("https://reqres.in/api/users/2");
  assertEquals(resp.getStatusCode(),204);
  System.out.println("Delete exaple is executed");


}

     @Test(description = "ValidateWithTestDataFromJsonFile")
  public void Test3() throws IOException, ParseException {
      String username = JsonReader.getTestData("username"); 
      String password = JsonReader.getTestData("password");
      System.out.println( username +" : "+ password);
  
      Response rep = given()
              .auth()
              .basic(username, password)
              .when()
              .get("https://postman-echo.com/basic-auth");
  
      int statusCode = rep.getStatusCode();
      assertEquals(statusCode, 200);
      System.out.println("Response Body : " + rep.getBody().asString());
      System.out.println("Test data from JSON file is used successfully.");
  }
            


    @Test(description = "validateWithDataFromPropertyFile")
  public void Test4() {
     
      

      String serverAddress = PropertyReader.propertyReader("config.properties","server");
          System.out.println("Server Address from Property File: " + serverAddress);
       
        given()
                .when()
                .get(serverAddress)
                .then()
                .statusCode(200)
                .body("name", equalTo("ReqRes API"))
                .body("endpoints.free", equalTo("/api/users"));
    }

}



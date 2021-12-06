package com.test;

import org.testng.Assert;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class FirstTest {

        @BeforeTest
        public void setUpBaseURI() {
            baseURI = "https://reqres.in/api";
        }
        @Test(priority = 0)
        public static void testListOfUsers() {
            Response response = get("/users?page=2");
            System.out.println(response.asPrettyString());
            System.out.println(response.getContentType());
            System.out.println(response.getTime());
            int statusCode = response.getStatusCode();
            Assert.assertEquals(statusCode, 200);
        }
        @Test(priority = 1)
        public static void testSingleUser() {
            given().get("/users?page=2").
                    then().statusCode(200).
                    body("data[1].id", equalTo(8)).
                    body("data[0].email", equalTo("michael.lawson@reqres.in")).
                    body("data[0].first_name", equalTo("Michael")).
                    body("data[0].last_name", equalTo("Lawson")).
                    body("data[0].avatar", equalTo("https://reqres.in/img/faces/7-image.jpg"));
        }
        @AfterTest
        public void message() {
            System.out.println("This Testing is done by KIRAN ");
        }
}

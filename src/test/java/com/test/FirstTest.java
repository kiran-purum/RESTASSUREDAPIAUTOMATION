package com.test;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class FirstTest extends BaseTest {

        @Test(priority = 0)
        public static void getListOfUserByPageNoTest() {
            int pageNumber = 1;
            Response response =  given().queryParam("page", pageNumber)
                    .when().get("users")
                    .then().extract().response();
            Assert.assertEquals(response.statusCode(), 200);

            JsonPath jsonPath = new JsonPath(response.asString());
            Assert.assertEquals(jsonPath.getInt("page"), pageNumber);
            Assert.assertEquals(jsonPath.getInt("per_page"), 6);
            Assert.assertEquals(jsonPath.getInt("total"), 12);
            Assert.assertEquals(jsonPath.getList("data").size(), 6);
            Assert.assertTrue(jsonPath.getString("data[0].email").contains("@reqres.in"));
            Assert.assertEquals(jsonPath.getString("data[0].email"), "george.bluth@reqres.in");
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

package com.test;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

public class HttpMethodsExamples extends BaseTest {

    @Test(priority = 4)
    public void getUserByIdAndNameByPageNo() {
        int pageNumber = 1;
        Response response = given().queryParam("page", pageNumber)
                .when().get("users")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = new JsonPath(response.asString());
        Assert.assertEquals(jsonPath.getInt("data[0].id"), 1);
        Assert.assertEquals(jsonPath.getString("data[0].email"), "george.bluth@reqres.in");
        Assert.assertEquals(jsonPath.getString("data[0].first_name"), "George");
        Assert.assertEquals(jsonPath.getString("data[0].last_name"), "Bluth");
        Assert.assertEquals(jsonPath.getString("data[0].avatar"), "https://reqres.in/img/faces/1-image.jpg");
    }

    @Test(priority = 5)
    public void createUserTestInFirstPage() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 7);
        map.put("email", "kiran12@reqres.in");
        map.put("first_name", "KIRAN");
        map.put("last_name", "KALYAN");
        map.put("avatar", "https://reqres.in/img/faces/1-image.jpg");
        JSONObject request = new JSONObject(map);
        int pageNumber = 1;
        Response response = given().queryParam("page", pageNumber)
                .when().post("users")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 415);

    }

    @Test(priority = 6)
    public void updateUserTestInFirstPage() {
        Map<String, Object> map = new HashMap<>();
        map.put("email", "kiran12@reqres.in");
        map.put("first_name", "KALYAN");
        map.put("last_name", "DUKER");
        map.put("avatar", "https://reqres.in/img/faces/1-image.jpg");
        JSONObject request = new JSONObject(map);
        int pageNumber = 1;
        Response response = given().queryParam("page", pageNumber)
                .when().put("users/2")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 7)
    public void updateUserPartialTestInFirstPage() {
        Map<String, Object> map = new HashMap<>();
        map.put("first_name", "KRISTEN");
        map.put("last_name", "STEWERT");
        JSONObject request = new JSONObject(map);
        int pageNumber = 1;
        Response response = given().queryParam("page", pageNumber)
                .when().post("users/2")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 415);
    }

    @Test(priority = 8)
    public void deletingUserDataByPageNO() {
        int pageNumber = 1;
        Response response = given().queryParam("page", pageNumber)
                .when().delete("users/2")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
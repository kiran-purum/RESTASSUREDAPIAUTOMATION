package com.test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class LocalUserAPIValidation extends BaseURITest {

    @Test
    public void getListOfMovies() {
        Response response = given().get("MOVIES")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
        System.out.println(response.asPrettyString());
        JsonPath jsonPath = new JsonPath(response.asString());
        Assert.assertEquals(jsonPath.getList("MOVIES").size(), 4);
    }

    @Test(priority = 1)
    public void getListOfSports() {
        Response response = given().get("SPORTS").
                then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = new JsonPath(response.asPrettyString());
        Assert.assertEquals(jsonPath.getList("SPORTS").size(), 5);
    }

    @Test(priority = 2)
    public void getMovieById() {
        Response response = given().get("/MOVIES/222")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
        System.out.println(response.asString());
    }

    @Test(priority = 3)
    public void getSportById() {
        Response response = given().get("/SPORTS/333")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
        System.out.println(response.asString());
    }

    @Test(priority = 4)
    public void getMovieByName() {
        Response response = given().get("/MOVIES?title=RRR")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
        System.out.println(response.asString());
    }

    @Test(priority = 5)
    public void getSportByName() {
        Response response = given().get("/SPORTS?batsmen=YUVRAJ SINGH")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
        System.out.println(response.asString());
    }

    @Test(priority = 6)
    public void createDataInMovies() {
        Map<String, Object> map = new HashMap<>();
        map.put("title", "THE EXPANSE");
        map.put("actor", "JORDAN AMOS");
        map.put("id", 43);
        JSONObject request = new JSONObject(map);
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString())
                .when().post("/MOVIES")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 201);
        System.out.println(response.asString());
    }

    @Test(priority = 7)
    public void createDataInSports() {
        Map<String, Object> map = new HashMap<>();
        map.put("batsmen", "DAVID MILLER");
        map.put("country", "SOUTH AFRICA");
        map.put("id", 10);
        JSONObject request = new JSONObject(map);
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString())
                .when().post("/SPORTS")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 201);
        System.out.println(response.asString());
    }

    @Test(priority = 8)
    public void updateDataTestInMovies() {
        Map<String, Object> map = new HashMap<>();
        map.put("title", "THE 100");
        map.put("actor", "CLARKE");
        JSONObject request = new JSONObject(map);
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).
                when().put("/MOVIES").
                then().statusCode(404).log().all();
    }
}
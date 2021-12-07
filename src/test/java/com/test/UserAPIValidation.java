package com.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class UserAPIValidation {

    @Test
    public void getListOfMovies() {
        Response response = get("http://localhost:3000/MOVIES");
        System.out.println(response.asPrettyString());
        System.out.println(response.getStatusCode());
    }

    @Test(priority = 1)
    public void getListOfSports() {
         given().get("http://localhost:3000/SPORTS").
         then().statusCode(200).log().all();
    }

    @Test(priority = 2)
    public void getSingleMovieById() {
        Response response = get("http://localhost:3000/MOVIES/222");
        System.out.println(response.asPrettyString());
        System.out.println(response.getStatusCode());
    }

    @Test(priority = 3)
    public void getSingleSportsById() {
        Response response = get("http://localhost:3000/SPORTS/12");
        System.out.println(response.asPrettyString());
        System.out.println(response.getStatusCode());
    }

    @Test(priority = 4)
    public void getSingleMovieByName() {
        Response response = get("http://localhost:3000/MOVIES?title=JOKER");
        System.out.println(response.asPrettyString());
        System.out.println(response.getStatusCode());
    }

    @Test(priority = 5)
    public void getSingleSportsByName() {
        Response response = get("http://localhost:3000/SPORTS?batsmen=YUVRAJ SINGH");
        System.out.println(response.asPrettyString());
        System.out.println(response.getStatusCode());
    }

    @Test(priority = 6)
    public void postDataInMovies() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "NO TIME TO DIE");
        map.put("actor", "JAMES BOND");
        map.put("id", 8);
        JSONObject request = new JSONObject(map);
        baseURI = "http://localhost:3000";
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).
        when().post("/MOVIES").
        then().statusCode(201).log().all();
    }

    @Test(priority = 7)
    public void postDataInSports() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("batsmen", "AIDEN BLIZZARD");
        map.put("country", "ENGLAND");
        map.put("id", 21);
        JSONObject request = new JSONObject(map);
        baseURI = "http://localhost:3000";
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).
        when().post("/SPORTS").
        then().statusCode(201).log().all();
    }

    @Test(priority = 8)
    public void putDataInMovies() {
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("title", "THE 100");
//        map.put("actor", "CLARKE");
//        map.put("id", 100);
        JSONObject request = new JSONObject(map);
        System.out.println(request.toJSONString());
        baseURI = "http://localhost:3000";
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).
        when().put("/MOVIES[5].actor").
        then().statusCode(404).log().all();
    }
}
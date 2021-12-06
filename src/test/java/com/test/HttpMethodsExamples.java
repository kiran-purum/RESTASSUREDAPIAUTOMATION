package com.test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.*;
import io.restassured.http.*;

public class HttpMethodsExamples {

    @BeforeTest
    public void setUpBaseURI() {
        baseURI = "https://reqres.in/api";
    }

    @Test(priority = 0)
    public void httpGetMethod() {
        given().
                get("/users?page=2").
                then().
                statusCode(200).
                body("data[1].id", equalTo(8)).
                body("data[0].email", equalTo("michael.lawson@reqres.in")).
                body("data[0].first_name", equalTo("Michael")).
                body("data[0].last_name", equalTo("Lawson")).
                body("data[0].avatar", equalTo("https://reqres.in/img/faces/7-image.jpg")).
                body("data.first_name", hasItems("Michael", "Lindsay"));
    }

    @Test(priority = 1)
    public void httpPostMethod() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "KIRAN");
        map.put("job", "TESTER");
        JSONObject request = new JSONObject(map);
        System.out.println(request.toJSONString());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).
                when().post("/users").
                then().statusCode(201).log().all();
    }

    @Test(priority = 2)
    public void httpPutMethod() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "KIRAN");
        map.put("job", "TESTER");
        JSONObject request = new JSONObject(map);
        System.out.println(request.toJSONString());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).
                when().put("/users/2").
                then().statusCode(200).log().all();
    }

    @Test(priority = 3)
    public void httpPatchMethod() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "KIRAN");
        map.put("job", "TESTER");
        JSONObject request = new JSONObject(map);
        System.out.println(request.toJSONString());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).
                when().patch("api/users/2").
                then().statusCode(200).log().all();
    }

    @Test(priority = 4)
    public void httpDeleteMethod() {
        when().delete("api/users/2").
                then().statusCode(204).log().all();
    }

    @AfterTest
    public void message() {
        System.out.println("This Testing is done by KIRAN ");
    }
}

//package com.test;
//
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//
//public class JSONSchemaValidator {
//    @Test
//    public void getListOfMovies() {
//        Response response = given()
//                .when().contentType(ContentType.JSON).get("http://localhost:3000/MOVIES")
//                .then().assertThat().body(matchesJsonSchemaInClasspath("Schema.json")).extract().response();
//        Assert.assertEquals(response.statusCode(), 200);
//    }
//
//    @Test(priority = 1)
//    public void getListOfSports() {
//        Response response1 = given()
//                .when().contentType(ContentType.JSON).get("http://localhost:3000/SPORTS")
//                .then().assertThat().body(matchesJsonSchemaInClasspath("Schema.json")).extract().response();
//        Assert.assertEquals(response1.statusCode(), 200);
//    }
//}
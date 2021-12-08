package com.test;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JSONSchemaValidator extends BaseURITest {
    @Test
    public void getListOfMovies() {
        Response response = given().get("http://localhost:3000/MOVIES")
                .then().assertThat().body(matchesJsonSchemaInClasspath("Schema.json"))
                .extract().response();
        Assert.assertEquals(response.statusCode(), 200);
    }
}
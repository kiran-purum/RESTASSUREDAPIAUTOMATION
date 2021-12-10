package com.demo.localApiValidationOnSports;

import com.demo.basetest.BaseURITest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.demo.resources.PayloadData.createUserDataInLocal;
import static com.demo.utils.ResponseToJsonPath.ConvertResponseToJsonConverter;
import static io.restassured.RestAssured.given;

public class LocalUserAPIValidation extends BaseURITest {

    @Test
    public void getListOfSports() {
        int dataSize = 3;
        Response response = given()
                .when().contentType(ContentType.JSON).get("/SPORTS")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.getList("SPORTS").size(), dataSize);
    }

    @Test(priority = 1)
    public void getSportById() {
        Response response = given()
                .when().contentType(ContentType.JSON).get("/SPORTS/12")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(jsonPath.getString("id"));
    }

    @Test(priority = 2)
    public void getSportByName() {
        String batsmenName = "YUVRAJ SINGH";
        Response response = given().queryParam("SPORTS", batsmenName)
                .when().contentType(ContentType.JSON).get("/SPORTS")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(jsonPath.getString("batsmen"));
    }

    @Test(priority = 3)
    public void createDataInSports() {
        String batsmen = "DAVID MILLER";
        String country = "SOUTH AFRICA";
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(createUserDataInLocal(batsmen, country)).post("/SPORTS")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(jsonPath.getString("batsmen"), batsmen);
        Assert.assertEquals(jsonPath.getString("country"), country);
        Assert.assertNotNull(jsonPath.getString("id"));
    }

    @Test(priority = 4)
    public void updateDataTestInSports() {
        String batsmen = "KEVIN PETERSON";
        String country = "ENGLAND";
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(createUserDataInLocal(batsmen, country)).put("/SPORTS/22")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.getString("batsmen"), batsmen);
        Assert.assertEquals(jsonPath.getString("country"), country);
        Assert.assertNotNull(jsonPath.getString("id"));
    }

    @Test(priority = 5)
    public void updatePartialDataTestInSports() {
        String batsmen = "NICOLAS POORAN";
        String country = "WEST INDIES";
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(createUserDataInLocal(batsmen, country)).put("/SPORTS/22")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.getString("batsmen"), batsmen);
        Assert.assertEquals(jsonPath.getString("country"), country);
        Assert.assertNotNull(jsonPath.getString("id"));
    }

    @Test(priority = 6)
    public void deleteTestDataInSports() {
        Response response = given()
                .when().contentType(ContentType.JSON).delete("/SPORTS/22")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
    }
}
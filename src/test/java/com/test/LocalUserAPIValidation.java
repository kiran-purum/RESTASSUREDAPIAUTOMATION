package com.test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.resources.PayloadData.*;
import static com.utils.ResponseToJsonPath.ConvertResponseToJsonConverter;
import static io.restassured.RestAssured.given;

public class LocalUserAPIValidation extends BaseURITest {

    @Test
    public void getListOfMovies() {
        int dataSize = 2;
        BaseURITest.createTestOnLocalApi("Get List of Movies", "Regression", "KALYAN", "chrome 96");
        Response response = given()
                .when().contentType(ContentType.JSON).get("/MOVIES")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.getList("MOVIES").size(), dataSize);
        Assert.assertNotNull(jsonPath.getString("MOVIES.id"));
    }

    @Test(priority = 1)
    public void getListOfSports() {
        int dataSize = 3;
        BaseURITest.createTestOnLocalApi("Get List of Sports", "Regression", "KALYAN", "chrome 96");
        Response response = given()
                .when().contentType(ContentType.JSON).get("/SPORTS")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.getList("SPORTS").size(), dataSize);
    }

    @Test(priority = 2)
    public void getMovieById() {
        BaseURITest.createTestOnLocalApi("Get Movie By Id", "Regression", "KALYAN", "chrome 96");
        Response response = given()
                .when().contentType(ContentType.JSON).get("/MOVIES/666")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(jsonPath.getString("id"));
    }

    @Test(priority = 3)
    public void getSportById() {
        BaseURITest.createTestOnLocalApi("Get Sports by id", "Regression", "KALYAN", "chrome 96");
        Response response = given()
                .when().contentType(ContentType.JSON).get("/SPORTS/12")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(jsonPath.getString("id"));
    }

    @Test(priority = 4)
    public void getMovieByName() {
        BaseURITest.createTestOnLocalApi("Get Movie By Name", "Regression", "KALYAN", "chrome 96");
        String movieName = "RRR";
        Response response = given().queryParam("MOVIES", movieName)
                .when().contentType(ContentType.JSON).get("/MOVIES")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(jsonPath.getString("title"));
    }

    @Test(priority = 5)
    public void getSportByName() {
        BaseURITest.createTestOnLocalApi("Get Sport By Name", "Regression", "KALYAN", "chrome 96");
        String batsmenName = "YUVRAJ SINGH";
        Response response = given().queryParam("SPORTS", batsmenName)
                .when().contentType(ContentType.JSON).get("/SPORTS")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(jsonPath.getString("batsmen"));
    }

    @Test(priority = 6)
    public void createDataInMovies() {
        BaseURITest.createTestOnLocalApi("Create Data In Movies", "Regression", "KALYAN", "chrome 96");
        String title = "PRISON BREAK SERIES";
        String actor = "WENTWORTH MILLER";
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(createUserDataInLocalApi(title, actor))
                .when().post("/MOVIES")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(jsonPath.getString("title"), title);
        Assert.assertEquals(jsonPath.getString("actor"), actor);
    }

    @Test(priority = 7)
    public void createDataInSports() {
        BaseURITest.createTestOnLocalApi("Create Data In Sports", "Regression", "KALYAN", "chrome 96");
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

    @Test(priority = 8)
    public void updateDataTestInMovies() {
        String title = "THE 100";
        String actor = "EMILY";
        BaseURITest.createTestOnLocalApi("Update Data Test In Movies", "Regression", "KALYAN", "chrome 96");
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(createUserDataInLocalApi(title, actor)).put("/MOVIES/667")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.getString("title"), title);
        Assert.assertEquals(jsonPath.getString("actor"), actor);
    }

    @Test(priority = 9)
    public void updateDataTestInSports() {
        String batsmen = "KEVIN PETERSON";
        String country = "ENGLAND";
        BaseURITest.createTestOnLocalApi("Update Data Test In Sports", "Regression", "KALYAN", "chrome 96");
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

    @Test(priority = 10)
    public void updatePartialDataTestInMovies() {
        String title = "PRODIGY";
        String actor = "LITTLE KID";
        BaseURITest.createTestOnLocalApi("Update Partial Data Test In Movies", "Regression", "KALYAN", "chrome 96");
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(updateUserDataInLocalApi(title, actor)).put("/MOVIES/667")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.getString("title"), title);
        Assert.assertEquals(jsonPath.getString("actor"), actor);
    }

    @Test(priority = 11)
    public void updatePartialDataTestInSports() {
        String batsmen = "NICOLAS POORAN";
        String country = "WEST INDIES";
        BaseURITest.createTestOnLocalApi("Update Partial Data Test In Sports", "Regression", "KALYAN", "chrome 96");
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

    @Test(priority = 12)
    public void deleteTestDataInMovies() {
        BaseURITest.createTestOnLocalApi("Delete Test Data In Movies ", "Regression", "KALYAN", "chrome 96");
        Response response = given()
                .when().contentType(ContentType.JSON).delete("/MOVIES/667")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 13)
    public void deleteTestDataInSports() {
        BaseURITest.createTestOnLocalApi("Delete Test Data In Movies", "Regression", "KALYAN", "chrome 96");
        Response response = given()
                .when().contentType(ContentType.JSON).delete("/SPORTS/22")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
    }
}
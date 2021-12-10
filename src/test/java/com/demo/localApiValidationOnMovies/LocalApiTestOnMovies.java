package com.demo.localApiValidationOnMovies;

import com.demo.basetest.BaseURITest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.demo.resources.PayloadData.createUserDataInLocalApi;
import static com.demo.resources.PayloadData.updateUserDataInLocalApi;
import static com.demo.utils.ResponseToJsonPath.ConvertResponseToJsonConverter;
import static io.restassured.RestAssured.given;

public class LocalApiTestOnMovies extends BaseURITest {
    @Test
    public void getListOfMovies() {
        int dataSize = 2;
        Response response = given()
                .when().contentType(ContentType.JSON).get("/MOVIES")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.getList("MOVIES").size(), dataSize);
        Assert.assertNotNull(jsonPath.getString("MOVIES.id"));
    }

    @Test(priority = 1)
    public void getMovieById() {
        Response response = given()
                .when().contentType(ContentType.JSON).get("/MOVIES/666")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(jsonPath.getString("id"));
    }

    @Test(priority = 2)
    public void getMovieByName() {
        String movieName = "RRR";
        Response response = given().queryParam("MOVIES", movieName)
                .when().contentType(ContentType.JSON).get("/MOVIES")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(jsonPath.getString("title"));
    }

    @Test(priority = 3)
    public void createDataInMovies() {
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

    @Test(priority = 4)
    public void updateDataTestInMovies() {
        String title = "THE 100";
        String actor = "EMILY";
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(createUserDataInLocalApi(title, actor)).put("/MOVIES/667")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.getString("title"), title);
        Assert.assertEquals(jsonPath.getString("actor"), actor);
    }

    @Test(priority = 5)
    public void updatePartialDataTestInMovies() {
        String title = "PRODIGY";
        String actor = "LITTLE KID";
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(updateUserDataInLocalApi(title, actor)).put("/MOVIES/667")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.getString("title"), title);
        Assert.assertEquals(jsonPath.getString("actor"), actor);
    }

    @Test(priority = 6)
    public void deleteTestDataInMovies() {
        Response response = given()
                .when().contentType(ContentType.JSON).delete("/MOVIES/667")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
    }
}

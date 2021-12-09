package com.test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.resources.PayloadData.createData;
import static com.resources.PayloadData.createDataInReqRes;
import static com.utils.ResponseToJsonPath.ConvertResponseToJsonConverter;
import static io.restassured.RestAssured.given;

public class HttpMethodsExamples extends BaseTest {

    @Test(priority = 3)
    public void getUserByIdAndNameByPageNo() {
        int pageNumber = 1;
        int id = 1;
        String email = "george.bluth@reqres.in";
        String firstName = "George";
        String lastName = "Bluth";
        String avatar = "https://reqres.in/img/faces/1-image.jpg";
        BaseTest.createTest("Get User By Id and Name by PageNo", "Regression", "KIRAN", "chrome 96");
        Response response = given().queryParam("page", pageNumber)
                .when().contentType(ContentType.JSON)
                .body(createDataInReqRes(id, email, firstName, lastName, avatar)).get("users")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(jsonPath.getInt("data[0].id"), id);
        Assert.assertEquals(jsonPath.getString("data[0].email"), email);
        Assert.assertEquals(jsonPath.getString("data[0].first_name"), firstName);
        Assert.assertEquals(jsonPath.getString("data[0].last_name"), lastName);
        Assert.assertEquals(jsonPath.getString("data[0].avatar"), avatar);
    }

    @Test(priority = 4)
    public void createUserTestInFirstPage() {
        String firstName = "KIRAN";
        String lastName = "KALYAN";
        BaseTest.createTest("Create User Test In First Page", "Regression", "KIRAN", "chrome 96");
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(createData(firstName, lastName)).body(createData(firstName, lastName)).post("users")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 201);
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertNotNull(jsonPath.getString("id"));
        Assert.assertEquals(jsonPath.getString("first_name"), firstName);
        Assert.assertEquals(jsonPath.getString("last_name"), lastName);
        Assert.assertNotNull(jsonPath.getString("createdAt"));
    }

    @Test(priority = 5)
    public void updateUserTestInFirstPage() {
        String firstName = "KIRAN";
        String lastName = "DUKER";
        BaseTest.createTest("Update User Test In First Page", "Regression", "KIRAN", "chrome 96");
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(createData(firstName, lastName)).body(createData(firstName, lastName)).put("users")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(jsonPath.getString("first_name"), firstName);
        Assert.assertEquals(jsonPath.getString("last_name"), lastName);
    }

    @Test(priority = 6)
    public void updateUserPartialTestInFirstPage() {
        String firstName = "KRISTEN";
        String lastName = "STEWART";
        BaseTest.createTest("Update User Partial Test In First Page", "Regression", "KIRAN", "chrome 96");
        Response response = given()
                .when().contentType(ContentType.JSON).body(createData(firstName, lastName)).post("users")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(jsonPath.getString("first_name"), firstName);
        Assert.assertNotNull(jsonPath.getString("createdAt"));
    }

    @Test(priority = 7)
    public void deletingUserDataByIdInPageNO() {
        int pageNumber = 1;
        BaseTest.createTest("Delete User Data By Id In PageNo", "Regression", "KIRAN", "chrome 96");
        Response response = given().queryParam("page", pageNumber)
                .when().contentType(ContentType.JSON).delete("/users/3")
                .then().extract().response();
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(response.statusCode(), 204);
    }
}
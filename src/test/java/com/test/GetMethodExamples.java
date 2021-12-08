package com.test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.utils.ResponseToJsonPath.ConvertResponseToJsonConverter;
import static io.restassured.RestAssured.given;

public class GetMethodExamples extends BaseTest {

    @Test
    public static void getListOfUserByPageNoTest() {
        int pageNumber = 1;
        String emailContains = "@reqres.in";
        String email = "george.bluth@reqres.in";
        Response response = given().queryParam("page", pageNumber)
                .when().get("users")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(jsonPath.getInt("page"), pageNumber);
        Assert.assertEquals(jsonPath.getInt("per_page"), 6);
        Assert.assertEquals(jsonPath.getInt("total"), 12);
        Assert.assertEquals(jsonPath.getInt("total_pages"), 2);
        Assert.assertEquals(jsonPath.getList("data").size(), 6);
        Assert.assertTrue(jsonPath.getString("data.email").contains(emailContains));
        Assert.assertEquals(jsonPath.getString("data[0].email"), email);
    }

    @Test(priority = 1)
    public static void getListUsersByPageNo() {
        int pageNumber = 2;
        String firstName = "Rachel";
        Response response = given().queryParam("page", pageNumber)
                .when().get("users")
                .then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = ConvertResponseToJsonConverter(response);
        Assert.assertEquals(jsonPath.getInt("page"), pageNumber);
        Assert.assertEquals(jsonPath.getInt("per_page"), 6);
        Assert.assertEquals(jsonPath.getInt("total"), 12);
        Assert.assertEquals(jsonPath.getInt("total_pages"), 2);
        Assert.assertEquals(jsonPath.getList("data").size(), 6);
        Assert.assertEquals(jsonPath.getList("data.id").size(), 6);
        Assert.assertEquals(jsonPath.getString("data[5].first_name"), firstName);
    }
}
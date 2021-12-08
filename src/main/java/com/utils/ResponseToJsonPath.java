package com.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseToJsonPath {
    public static JsonPath ConvertResponseToJsonConverter(Response response) {
        return new JsonPath(response.asString());
    }
}
package com.demo.basetest;

import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    @BeforeTest
    public void setUp() {
        baseURI = "https://reqres.in/api";
    }

}

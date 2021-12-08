package com.test;


import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.baseURI;

public class BaseURITest {

    @BeforeTest
    public void setBaseurl() {
        baseURI = "http://localhost:3000/";
    }
}
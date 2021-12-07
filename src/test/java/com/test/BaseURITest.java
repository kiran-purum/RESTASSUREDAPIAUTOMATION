package com.test;


import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.BeforeTest;

public class BaseURITest {

    @BeforeTest
    public void setBaseurl() {
        baseURI = "http://localhost:3000/";
    }
}
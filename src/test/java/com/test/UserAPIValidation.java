package com.test;

import org.testng.annotations.*;

import static io.restassured.RestAssured.*;

public class UserAPIValidation {

    @BeforeTest
    public void setBaseURI() {
        baseURI = " http://localhost:3000";
    }

}

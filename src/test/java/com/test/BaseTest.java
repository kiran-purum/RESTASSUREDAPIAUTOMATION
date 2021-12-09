package com.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    static ExtentReports extent;
    static ExtentTest test;

    @BeforeSuite
    public static void configReport() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("report/index.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Automation test report");
        spark.config().setReportName("Extent Reports");
        extent.attachReporter(spark);
    }

    @AfterSuite
    public static void flushReport() {
        extent.flush();
    }

    public static void createTest(String testName, String testCategory, String authorName, String testDeviceName) {
        test = extent.createTest(testName)
                .assignCategory(testCategory)
                .assignAuthor(authorName)
                .assignDevice(testDeviceName);
    }

    @BeforeTest
    public void setUp() {
        baseURI = "https://reqres.in/api";
    }

}

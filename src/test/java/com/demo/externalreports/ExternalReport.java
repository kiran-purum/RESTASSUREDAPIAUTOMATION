package com.demo.externalreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ExternalReport {
    @Test
    public void extenteportTest() throws IOException {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("apiTests.html");
        final File CONF = new File("extent-Config.json");
        spark.loadJSONConfig(CONF);
        extent.attachReporter(spark);

        ExtentTest test = extent.createTest("Login Test").assignAuthor("KIRAN").assignCategory("Smoke").assignDevice("chrome 96");
        test.pass("LOGIN TEST PASSED SUCCESSFULLY");
        test.info("URL is Loaded");
        test.info("Values Entered");
        test.pass("login test completed successfully");

        ExtentTest newTest = extent.createTest("Home page test").assignAuthor("KALYAN").assignCategory("Regression").assignDevice("Firefox 61");
        newTest.pass("HOME PAGE TESTED SUCCESSFULLY");
        newTest.info("URL is Loaded");
        newTest.info("Values Entered");
        newTest.fail("Home page test failed unfortunately");

        extent.flush();
        Desktop.getDesktop().browse(new File("apiTests.html").toURI());
    }
}

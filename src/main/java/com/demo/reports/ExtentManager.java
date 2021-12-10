package com.demo.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentTest test;

    public static void configReport() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("report/index.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Automation test report");
        spark.config().setReportName("Extent Reports");
        extent.attachReporter(spark);
    }

    public static void flushReport() {
        extent.flush();

    }

    public static void createTest(String testName, String testCategory, String authorName, String testDeviceName) {
        test = extent.createTest(testName)
                .assignCategory(testCategory)
                .assignAuthor(authorName)
                .assignDevice(testDeviceName);
    }
}

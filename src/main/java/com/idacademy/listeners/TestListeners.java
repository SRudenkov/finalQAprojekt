package com.idacademy.listeners;

import com.idacademy.utils.DriverFactory;
import com.idacademy.utils.DriverManager;
import com.idacademy.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListeners implements ITestListener {

    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("==========================================================");
        LOGGER.info("Test " + result.getMethod().getMethodName() + " started");
        LOGGER.info("==========================================================");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("==========================================================");
        LOGGER.info("Test " + result.getMethod().getMethodName() + " success");
        LOGGER.info("==========================================================");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("==========================================================");
        LOGGER.info("Test " + result.getMethod().getMethodName() + " failure");
        LOGGER.info("==========================================================");
        try {
            ScreenshotUtils.takeScreenshot(DriverManager.getDriver());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
    DriverManager.quitDriver();
    }

}

package com.idacademy.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;


public class ScreenshotUtils {
    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);

    public static void  takeScreenshot(WebDriver driver) throws IOException {
        File file = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        File screenshot = new  File("C:\\Users\\User\\IdeaProjects\\finalProjektRSV\\src\\test\\java\\resources\\screenshots\\" + date.getTime() +".png");
        try {
            FileUtils.copyFile(file, screenshot);
        } catch (IOException e) {
            LOGGER.debug("Screenshot  is not save...");;
        }
    }
}
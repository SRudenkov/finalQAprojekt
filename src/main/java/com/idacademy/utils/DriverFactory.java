package com.idacademy.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);
    public static WebDriver createDriver(String browser) {
        URL url;
        try {
            url = new URL("http://localhost:4444");
        } catch (MalformedURLException e) {
            throw new RuntimeException();
        }
        if(browser.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            return new RemoteWebDriver(url, chromeOptions);
        } else if(browser.equals("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            return new RemoteWebDriver(url, firefoxOptions);
        }
        return null;
    }

}

package com.idacademy.utils;

import com.idacademy.Enums.Capability;
import com.idacademy.listeners.ElementListenersActions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.events.EventFiringDecorator;

import java.time.Duration;

public class DriverManager {
    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);
    static WebDriver driver;
    static ThreadLocal<WebDriver> localDriver = new ThreadLocal<>();

    public static WebDriver getDriver(){
       if(localDriver.get() == null){
            driver = DriverFactory.createDriver(PropertyReader.getConfigProperty(Capability.BROWSER));
           //assert driver != null;
           EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(new ElementListenersActions());
           driver = decorator.decorate(driver);
           driver.manage().window().maximize();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            localDriver.set(driver);
            return driver;
       } else { return localDriver.get(); }

    }

    public static void quitDriver() {
        localDriver.get().quit();
        localDriver.set(null);
    }
}

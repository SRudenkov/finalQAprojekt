package com.idacademy.listeners;

import com.idacademy.utils.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.List;

public class ElementListenersActions implements WebDriverListener {
    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);
    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        LOGGER.info(keysToSend + " was typed element " + element.toString());
        // WebDriverListener.super.afterSendKeys(element, keysToSend);
    }

    @Override
    public void afterClick(WebElement element) {
        LOGGER.info("Click to " + element.toString());

    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        LOGGER.info("Find element " + locator +" "+ result + " " + result.toString());
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        LOGGER.info("Find elements " + locator +" "+ result.toString());
    }

}

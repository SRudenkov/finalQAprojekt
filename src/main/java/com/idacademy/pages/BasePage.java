package com.idacademy.pages;

import com.idacademy.utils.DriverFactory;
import org.apache.commons.math3.util.Precision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

public class BasePage {
    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);
    public Float priceItems(List<WebElement> listItems){

        List<String> allElementsPriceText = listItems.stream().map(WebElement::getText).collect(Collectors.toList());
        List<Float> allElementsPrice;
        List<String> elementsPriceText = new ArrayList<>();
        for (String x: allElementsPriceText) {
             String[] priceTemp = x.split(" ");
             elementsPriceText.add(priceTemp[0]);
        }

        allElementsPrice = elementsPriceText.stream().map(Float::parseFloat).collect(Collectors.toList());
        List<Float> allElementsPriceRound = new ArrayList<>();
        allElementsPriceRound = allElementsPrice.stream().map(x -> Precision.round(x, 2)).collect(Collectors.toList());

        System.out.println(allElementsPriceRound);
        float count = 0F;
        for(Float x: allElementsPriceRound)
        {count += Precision.round(x,2);}
        return count;
    }

    public  String inputMessage (String massage, WebElement webElement){
        Assert.assertTrue(webElement.isDisplayed(), " Finding area is not present ");
        webElement.click();
        webElement.sendKeys(massage);
        return massage;
    }

    public void clickButton(WebDriver driver, WebElement button) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

}

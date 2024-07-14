package com.idacademy.pages;


import com.idacademy.utils.DriverFactory;
import com.idacademy.utils.DriverManager;
import org.apache.commons.math3.util.Precision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;


public class ZernaMainPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);
    @FindBy(xpath = "//*[@name=\"search\"]") private WebElement serch;
    @FindBy(xpath = "//button[@type=\"button\"]") private WebElement serchButton;
    @FindBy(xpath = "//*[@class=\"product \"]") private WebElement getWebItem;
    @FindBy(xpath = "//div[@class=\"product \"]") private List<WebElement> getListWebItems;
    @FindBy(xpath = "//div[@class=\"price\"]") private WebElement getWebItemPrice;
    @FindBy(xpath = "//div[@class=\"price\"]") private List<WebElement> getWebItemsPrice;
    @FindBy(xpath = "//button[@class=\"add_to_cart\"]") private List<WebElement> buttonClickAddToCart;
    @FindBy(xpath = "//div[@class=\"xdsticker_instock\"]") private List<WebElement> avalableItems;
    @FindBy(xpath = "//div[@class=\"xdsticker_no_price\"]") private List<WebElement> notAvalableItems;
    @FindBy(xpath = "//div[@class=\"xdsticker_outstock\"]") private  List<WebElement> orderAvalableItems;
    @FindBy(xpath = "//a[@id=\"account_link\"]") private WebElement loginButton;

    public ZernaMainPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    BasePage basePage = new BasePage();
    public void openUrlZerna()  {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        DriverManager.getDriver().get("https://zerna.by/category/zernovoy-kofe");
    }

    public String insertSerchBrend(String brend) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(serch.isDisplayed(), " Serch area is not present ");
        serch.click();
        serch.sendKeys(brend);
        return brend;
    }
    public void submitSerchBrend(){
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        serchButton.click();
    }

    public void clickAllItems() throws ElementClickInterceptedException {

        for (WebElement webElement: buttonClickAddToCart) {
            DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", webElement);
        }
    }

    public List<String> getListItemsText(){
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<String> allElementText = getListWebItems.stream().map(x->x.getText()).collect(Collectors.toList());
        return allElementText;
    }

    public  Float priceMainItems() {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return Precision.round(basePage.priceItems(getWebItemsPrice),2);
    }

    public int avalable() {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        List<WebElement> AvalableElements = avalableItems;
        LOGGER.info(avalableItems.size());
        return avalableItems.size();
    }
    public int notAvalable()  {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        List<WebElement> notAvalableElements = notAvalableItems;
        LOGGER.info(notAvalableItems.size());
        return notAvalableItems.size();
    }

    public int orderAvalable()  {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        List<WebElement> oderAvalableElements = orderAvalableItems;
        LOGGER.info(oderAvalableElements.size());
        return oderAvalableElements.size();
    }

    public void clickLoginButton() {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        basePage.clickButton(DriverManager.getDriver(), loginButton);
    }
}

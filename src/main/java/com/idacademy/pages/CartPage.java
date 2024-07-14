package com.idacademy.pages;

import com.idacademy.listeners.TestListeners;
import com.idacademy.utils.DriverFactory;
import com.idacademy.utils.DriverManager;
import org.apache.commons.math3.util.Precision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class CartPage extends BasePage{
    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);

    @FindBy(xpath = "//a[@id=\"cart\"]") private WebElement cartButton;
    @FindBy (xpath = "//a[@class=\"d-none d-md-block\"]") private WebElement cartItem;

    @FindBy (xpath = "//a[@class=\"image\"]") private List<WebElement> listCartItems;

    @FindBy (xpath = "//td[@class=\"total uk-table-middle\"]") private List<WebElement> cartItemsPrice;

    @FindBy (xpath = "//span[@class=\"simplecheckout-cart-total-value\"]") private WebElement totalCartCost;
    @FindBy(xpath = "//div[@class = \"no_price\"]") private WebElement nonAvalable;

    public CartPage() {

        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void clickCartButton(){
     cartButton.click();
    }
    public List<String> getListCartItemsText(){
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<String> allElementText = listCartItems.stream().map(x->x.getText()).collect(Collectors.toList());
        return allElementText;
    }

    public Float priceCartItems(){
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
       BasePage basePage = new BasePage();
        return Precision.round(basePage.priceItems(cartItemsPrice),2);
    }

    public Float totalPriceCartElements(){
        String cost = totalCartCost.getText();
        LOGGER.info("Text " + cost);
        String[] costArrayCoin = cost.split(" руб.");
        String[] costArray = costArrayCoin[0].split(" ");
        String totalCost = String.join("",costArray);
        LOGGER.info("join_text " + totalCost);
       float costAll = Float.parseFloat(totalCost);
        LOGGER.info("Float " + costAll);
        costAll = Precision.round(costAll,2);
        LOGGER.info("Float.round " + costAll);
        return costAll;
    }


}

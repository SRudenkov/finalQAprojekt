package com.idacademy.UITests;

import com.idacademy.UITests.BaseTest;
import com.idacademy.listeners.TestListeners;
import com.idacademy.pages.CartPage;
import com.idacademy.pages.LoginPage;
import com.idacademy.pages.ZernaMainPage;
import com.idacademy.utils.DriverFactory;
import com.idacademy.utils.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

/**
 * java -jar selenium-server-4.20.0.jar standalone
 */
@Listeners(TestListeners.class)
public class BodyTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);
   String[] coffeNames = new String[]{"Lavazza", "Moak", "Egoiste", "Alvorada", "Dallmayr", "gevalia"};
       private String nameBrend = coffeNames[0];

    @DataProvider (name = "data")
    public static Object[][] getData() {
        return new Object[][]{
                {"qwerty@ytrewq.by", "","Неправильно заполнены поля E-Mail и/или пароль!\n×"},
                {"", "qwertyytrewqby", "Неправильно заполнены поля E-Mail и/или пароль!"},
                {"qwerty@ytrewq.by", "qwertyytrewqby", "Неправильно заполнены поля E-Mail и/или пароль!"},

        };
    }
    @DataProvider (name = "data1")
    public static Object[][] getData1() {
        return new Object[][]{
                {"rsv5153@mail.ru", "3515vsr", "Positive authorization option"}
        };
    }

    //throws InterruptedException, IOException
    @Test(testName = "equels elements in page and cart")
    public void TestFirstUI()  {
        ZernaMainPage zernaMainPage = new ZernaMainPage ();
        zernaMainPage.openUrlZerna();
        zernaMainPage.insertSerchBrend(nameBrend);
        zernaMainPage.submitSerchBrend();
        List<String> listMainPageElenents =  zernaMainPage.getListItemsText();
        zernaMainPage.clickAllItems();
        int quantityMainElements = listMainPageElenents.size();
        int notAvalable = zernaMainPage.notAvalable();
        int avalable = zernaMainPage.avalable();
        int orderAvalable = zernaMainPage.orderAvalable();
        CartPage cartPage = new CartPage();
        cartPage.clickCartButton();
        List<String> listCartPageElements = cartPage.getListCartItemsText();
        int quantityCartElemetns = listCartPageElements.size();
        LOGGER.info("quantityMainElements = " + quantityMainElements);
        LOGGER.info("notAvalable = " + notAvalable);
        LOGGER.info("avalable = " + avalable);
        LOGGER.info("orderAvalable = " + orderAvalable);
        LOGGER.info("quantityCartElemetns = " + quantityCartElemetns );
         int totalMainPageElements = avalable + notAvalable + orderAvalable;
        if (totalMainPageElements == quantityCartElemetns) {
            LOGGER.info(" elements in mainPage an elements in cartPage is equel " );

        }else LOGGER.info( "Somthing wrong ");
    }

    @Test(testName = "equels price elements in page and cart")
    public void TestSecondUI()  {
        ZernaMainPage zernaMainPage = new ZernaMainPage ();
        CartPage cartPage = new CartPage();
        zernaMainPage.openUrlZerna();
        zernaMainPage.insertSerchBrend(nameBrend);
        zernaMainPage.submitSerchBrend();
        Float priceAllMainItems =  zernaMainPage.priceMainItems();
        LOGGER.info("priceAllMainItems = " + priceAllMainItems);
        zernaMainPage.clickAllItems();
        cartPage.clickCartButton();
        Float priceAllCartItems =  cartPage.priceCartItems();
        LOGGER.info("priceAllCartItems = " + priceAllCartItems);
        Float priceTotalCartItems = cartPage.totalPriceCartElements();
        LOGGER.info("Prics total items in cart " + priceTotalCartItems);
        if (priceAllMainItems.equals(priceAllCartItems)) {
            LOGGER.info( " pricees in main and cart pager is equels ");
        }else LOGGER.info("test mistake");
        if ( priceAllMainItems.equals(priceTotalCartItems)){
            LOGGER.info("Calculation of the cost of goods in the cart is correct");
        } else LOGGER.info("Calculation of the cost of goods in the cart is not true ");
    }


    @Test (testName = "negative authorization", dataProvider = "data")
    public void TestThirdUI(String nameLogin, String password, String errorMassage)  {
        ZernaMainPage zernaMainPage = new ZernaMainPage ();
        LoginPage loginPage = new LoginPage();
        zernaMainPage.openUrlZerna();
        zernaMainPage.clickLoginButton();
        loginPage.inputLogin(nameLogin);
        loginPage.inputPass(password);
        loginPage.pressButtonAutorization();
        loginPage.answerAutorizationFals(errorMassage);
    }

    @Test (testName = "positive authorization", dataProvider = "data1")
    public void TestFourthUI(String nameLogin, String password, String errorMassage)  {
        ZernaMainPage zernaMainPage = new ZernaMainPage ();
        LoginPage loginPage = new LoginPage();
        zernaMainPage.openUrlZerna();
        zernaMainPage.clickLoginButton();
        loginPage.inputLogin(nameLogin);
        loginPage.inputPass(password);
        loginPage.pressButtonAutorization();
        loginPage.answerAutorizationTruth(errorMassage);

    }

}

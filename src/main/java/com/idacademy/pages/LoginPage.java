package com.idacademy.pages;

import com.beust.ah.A;
import com.idacademy.utils.DriverFactory;
import com.idacademy.utils.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);
      @FindBy(xpath = "//input[@class=\"form-control\"]")
    private WebElement login;
    @FindBy(xpath = "//input[@name=\"password\"]")
    private WebElement password;
    @FindBy(xpath = "//button[@class = \"btn-yellow\"]")
    private WebElement clickAutorization;
    @FindBy(xpath = "//div[@class= \"alert alert-danger alert-dismissible fade show\"]")
    private WebElement alertMessageLogPass;
    @FindBy(xpath = "//div[@class= \"customer_next_level\"]")
    private WebElement positiveAnswer;

    public LoginPage() {

        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    BasePage basePage = new BasePage();

    public String inputLogin(String email) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        basePage.inputMessage(email, login);
        return email;
    }

    public String inputPass(String pass) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        basePage.inputMessage(pass, password);
        return pass;
    }

    public void pressButtonAutorization() {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        basePage.clickButton(DriverManager.getDriver(), clickAutorization);
    }

    public void answerAutorizationFals(String errorMessage) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String negativeText = null;
        boolean b = alertMessageLogPass.isDisplayed();
        if (b) {
            LOGGER.info("alertMessageLogPass.isDisplayed()" + true);
            negativeText = alertMessageLogPass.getText();
        }
        assert negativeText != null;
        if (negativeText.equals(errorMessage)) {
            LOGGER.info(" " + errorMessage + " ");
            LOGGER.info("Negaitive authorization option");
        }
    }

    public void answerAutorizationTruth(String errorMessage) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String positiveText = null;
        boolean a = positiveAnswer.isDisplayed();
        if (a) {
            LOGGER.info("positiveAnswer.isDisplayed()" + true);
            positiveText = "Positive authorization option";
        }
        assert positiveText != null;
        if (positiveText.equals(errorMessage)) {
            LOGGER.info(" " + errorMessage + " ");
            LOGGER.info("login to your personal account ");
        }
    }
}


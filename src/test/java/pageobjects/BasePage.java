package pageobjects;

import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected static final String logoImageSource = "http://automationpractice.com/img/logo.jpg";

    protected WebDriver driver;
    protected Waiter waiter;

    protected By homeLogo = By.id("header_logo");
    protected By loginButton = By.className("login");


    protected BasePage(WebDriver driver, Waiter waiter){
        this.driver = driver;
        this.waiter = waiter;
    }

    public void validateGenericPage(){
        waiter.waitForElement(homeLogo);
        WebElement homeLogoElement = driver.findElement(homeLogo);

        assert homeLogoElement.findElement(By.tagName("img")).getAttribute("src").equals(logoImageSource);
    }
}

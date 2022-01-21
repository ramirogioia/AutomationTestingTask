package pageobjects;

import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected static final String logoImageSource = "http://practice.automationtesting.in/wp-content/uploads/2017/01/color-logo-original.png";

    protected WebDriver driver;
    protected Waiter waiter;

    protected By homeLogo = By.className("header-bar");
    protected By loginButton = By.id("menu-item-50");


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

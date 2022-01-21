package pageobjects;

import helpers.Waiter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver, Waiter waiter){
        super(driver, waiter);
    }

    public void validateHomePage(){
        this.validateGenericPage();
        //add more validations
    }

    public void clickOnSignInButton(){
        waiter.waitToBeClickable(loginButton);
        driver.findElement(loginButton).click();
    }
}

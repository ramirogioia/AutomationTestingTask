package pageobjects;

import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountDetailsPage extends BasePage{

    By form = By.id("account-creation_form");
    By titleRadioBoxes = By.xpath("//input[@name = 'id_gender']");
    By firstNameField = By.id("customer_firstname");
    By lastNameField = By.id("customer_lastname");
    By emailField = By.id("email");
    By passwordField = By.id("passwd");
    By newsletterCheckBox = By.id("newsletter");
    By offersCheckBox = By.id("optin");
    By daysSelect = By.name("days");
    By monthsSelect = By.name("months");
    By yearsSelect = By.name("years");


    public AccountDetailsPage(WebDriver driver, Waiter waiter){
        super(driver, waiter);
    }

    public void validateAccountDetailsPage(){
        this.validateGenericPage();
        //add more validations
    }

    public void fillPersonalInformation(boolean isMr, String firstName, String lastName, String email, String password,
                                        String birthday, boolean newsletter, boolean specialOffers){
        waiter.waitForElement(form);
        List<WebElement> titleRadioBoxesElement = driver.findElements(titleRadioBoxes);

        if(isMr){
            titleRadioBoxesElement.get(0).click();
        }else{
            titleRadioBoxesElement.get(1).click();
        }

        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);

        String[] dateInformation = birthday.split("/");
        List<Select> birthdaySelects = Arrays.asList(new Select(driver.findElement(daysSelect)),
                new Select(driver.findElement(monthsSelect)), new Select(driver.findElement(yearsSelect)));
        int counter = 0;

        for (Select select : birthdaySelects){
            select.selectByValue(dateInformation[counter]);
            counter ++;
        }

        if(newsletter){
            driver.findElement(newsletterCheckBox).click();
        }

        if(specialOffers){
            driver.findElement(offersCheckBox).click();
        }
    }
}

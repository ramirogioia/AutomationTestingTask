package pageobjects;

import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    private By emailAddressField = By.xpath("//input[@class = 'is_required validate account_input form-control']");
    //private By emailAddressField = By.className("is_required validate account_input form-control");
    private By createAccountButton = By.id("SubmitCreate");
    private By emailAddressForm = By.className("form-group");
    private By emailAddressFormSuccess = By.className("form-group.form-ok");
    private By emailAddressFormFailed = By.className("form-group.form-error");


    public LoginPage(WebDriver driver, Waiter waiter){
        super(driver, waiter);
    }

    public void validateLoginPage(){
        this.validateGenericPage();
        //add more validations
    }

    public void validateAndEnterEmailAddress(String email){
        waiter.waitForElement(emailAddressField);

        WebElement emailAddressFormElement = driver.findElement(emailAddressField);
        emailAddressFormElement.sendKeys(email);
        emailAddressFormElement.sendKeys(Keys.TAB);

        waiter.isPresent(emailAddressFormSuccess);
    }

    public void clickOnCreateAccount(){
        waiter.waitToBeClickable(createAccountButton);

        driver.findElement(createAccountButton).click();
    }
}

package pageobjects;

import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class AccountDetailsPage extends BasePage{

    By form = By.className("woocommerce-billing-fields");
    By cartItems = By.xpath("//tr[@class = 'cart_item']");
    By cartSubtotal = By.className("cart-subtotal");
    By cartSubtotalValue = By.xpath("//span[@class = 'woocommerce-Price-amount amount']");
    By countryDropdown = By.id("select2-chosen-1");
    By dropdownOptions = By.xpath("//li[contains(@class,'select2-results-dept-0 select2-result select2-result-selectable')]");
    By stateDropdown = By.id("s2id_billing_state");
    By placeOrderButton = By.id("place_order");

    By firstNameField = By.id("billing_first_name");
    By lastNameField = By.id("billing_last_name");
    By companyField = By.id("billing_company");
    By emailField = By.id("billing_email");
    By phoneField = By.id("billing_phone");
    By addressField = By.id("billing_address_1");
    By cityField = By.id("billing_city");
    By zipField = By.id("billing_postcode");
    By createAccountCheckBox = By.id("createaccount");


    public AccountDetailsPage(WebDriver driver, Waiter waiter){
        super(driver, waiter);
    }

    public void validateAccountDetailsPage(){
        this.validateGenericPage();
        driver.getCurrentUrl().equals("http://practice.automationtesting.in/checkout/");
    }

    public void fillPersonalInformation(String firstName, String lastName, String companyName, String email, String phone,
                                        String country, String address, String city, String state, int zip, boolean createAccount){
        waiter.waitForElement(form);

        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(companyField).sendKeys(companyName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(phoneField).sendKeys(phone);

        driver.findElement(countryDropdown).click();
        List<WebElement> options = driver.findElements(dropdownOptions);

        for (WebElement option:options) {
            if(option.getText().equals(country)){
                option.click();
                break;
            }
        }

        driver.findElement(addressField).sendKeys(address);
        driver.findElement(cityField).sendKeys(city);

        driver.findElement(stateDropdown).click();
        options = driver.findElements(dropdownOptions);

        for (WebElement option:options) {
            if(option.getText().equals(state)){
                option.click();
                break;
            }
        }

        if(createAccount){
            driver.findElement(createAccountCheckBox).click();
        }

        driver.findElement(zipField).sendKeys(String.valueOf(zip));
    }

    public void validateItemsAndCartValue(int itemQuantity, Double cartValue){
        Double actualCartValue = Double.valueOf(driver.findElement(cartSubtotal).findElement(cartSubtotalValue).getText().replaceAll("₹", ""));
        int actualItemQuantity = driver.findElements(cartItems).size();

        assert itemQuantity == actualItemQuantity;
        //assert cartValue.equals(actualCartValue);
    }

    public void clickOnPlaceOrder(){
        waiter.waitToBeClickable(placeOrderButton);
        driver.findElement(placeOrderButton).click();
    }
}

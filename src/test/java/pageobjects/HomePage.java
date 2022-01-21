package pageobjects;

import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.fail;

public class HomePage extends BasePage{

    By itemsPanel = By.xpath("//div[@class = 'themify_builder_sub_row clearfix gutter-default   sub_row_1-0-2']");
    By pageItems = By.xpath("//ul[@class = 'products']");
    By itemLink = By.className("woocommerce-LoopProduct-link");
    By addToBasketButton = By.xpath("//a[@class = 'button product_type_simple add_to_cart_button ajax_add_to_cart']");
    By addedToCardCheck = By.xpath("//a[@class = 'added_to_cart wc-forward']");
    By cartButton = By.className("wpmenucart-contents");
    By cartAmount = By.className("amount");
    By itemAmount = By.xpath("//span[contains(text(),'₹')]");


    public HomePage(WebDriver driver, Waiter waiter){
        super(driver, waiter);
    }

    public void validateHomePage(){
        this.validateGenericPage();
        driver.getCurrentUrl().equals("http://practice.automationtesting.in/");
    }

    public void validateCartIsEmpty(){
        waiter.waitForElement(cartAmount);
        double initialValue = 0.00;

        assert initialValue == Double.valueOf(driver.findElement(cartAmount).getText().replaceAll("₹", ""));
    }

    public void clickOnSignInButton(){
        waiter.waitToBeClickable(loginButton);
        driver.findElement(loginButton).click();
    }

    public double addItemToBasketAndValidateCart(List<String> items){
        waiter.waitForElement(itemsPanel);
        waiter.waitForElement(cartAmount);

        double actualCartValue = Double.valueOf(driver.findElement(cartAmount).getText().replaceAll("₹", ""));

        List<WebElement> itemElements = driver.findElements(pageItems);
        List<String> itemsFoundAndAdded = new ArrayList<String>();

        for (WebElement itemElement: itemElements) {
            String pageItemString = itemElement.findElement(itemLink).findElement(By.tagName("h3")).getText();

            if(items.contains(pageItemString)){
                itemElement.findElement(addToBasketButton).click();
                itemsFoundAndAdded.add(pageItemString);

                List<WebElement> itemsAmount = itemElement.findElements(itemAmount);
                double itemValue = Double.valueOf(itemsAmount.get(0).getText().replaceAll("₹", ""));
                actualCartValue = actualCartValue + itemValue;

                validateItemAddedToTheCart(itemElement);
                validateCartAmount(actualCartValue);
            }

            if(items.size() == itemsFoundAndAdded.size()){
                return actualCartValue;
            }
        }
        if(itemsFoundAndAdded == null){
            fail("None of the specified items have been found on the page.");
            return 0.00;
        }

        List<String> differences = items.stream()
                .filter(element -> !itemsFoundAndAdded.contains(element))
                .collect(Collectors.toList());

        fail("One or more items you have specified are not displayed on the page: " + differences.toString());
        return 0.00;
    }

    public void clickOnCartButton(){
        waiter.waitToBeClickable(cartButton);
        driver.findElement(cartButton).click();
    }

    private void validateItemAddedToTheCart(WebElement itemElement) {
        waiter.waitForElement(addedToCardCheck);
        WebElement itemCheck = itemElement.findElement(addedToCardCheck);

        assert itemCheck.isDisplayed();
    }

    private void validateCartAmount(double internalValue) {
        waiter.waitForElement(cartAmount);
        double cartValue = Double.valueOf(driver.findElement(cartAmount).getText().replaceAll("₹", ""));

        //assert internalValue == cartValue;
    }

}

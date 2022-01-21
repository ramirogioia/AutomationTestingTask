package pageobjects;

import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;



public class CartPage extends BasePage{

    By cartButtons = By.xpath("//input[@class = 'button']");
    By cartSubtotal = By.className("cart-subtotal");
    By cartSubtotalValue = By.xpath("//span[@class = 'woocommerce-Price-amount amount']");
    By cartItems = By.xpath("//tr[@class = 'cart_item']");
    By proceedToCheckoutButton = By.className("wc-proceed-to-checkout");


    public CartPage(WebDriver driver, Waiter waiter){
        super(driver, waiter);
    }

    public void validateCartPage(){
        this.validateGenericPage();
        driver.getCurrentUrl().equals("http://practice.automationtesting.in/basket/");

        List<WebElement> buttons = driver.findElements(cartButtons);

        assert buttons.size() == 2;
        assert buttons.get(0).getText().equals("APPLY COUPON");
        assert buttons.get(0).getText().equals("UPDATE BASKET");
    }

    public void validateItemsAndCartValue(int itemQuantity, double cartValue){
        double actualCartValue = Double.valueOf(driver.findElement(cartSubtotal).findElement(cartSubtotalValue).getText().replaceAll("₹", ""));
        int actualItemQuantity = driver.findElements(cartItems).size();

        assert itemQuantity == actualItemQuantity;
        //assert cartValue == actualCartValue;
    }

    public void clickOnProceedToCheckout(){
        waiter.waitToBeClickable(proceedToCheckoutButton);
        driver.findElement(proceedToCheckoutButton).findElement(By.tagName("a")).click();
    }

}

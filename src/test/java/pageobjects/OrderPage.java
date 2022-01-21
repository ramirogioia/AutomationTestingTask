package pageobjects;

import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage extends BasePage{

    By orderMessage = By.className("woocommerce-thankyou-order-received");
    By orderNumber = By.className("order");
    By cartItems = By.xpath("//tr[@class = 'order_item']");
    By cartSubtotal = By.xpath("//th[contains(text(),'Subtotal')]");
    By cartSubtotalValue = By.xpath("//span[@class = 'woocommerce-Price-amount amount']");


    public OrderPage(WebDriver driver, Waiter waiter){
        super(driver, waiter);
    }

    public void validateOrderPage(){
        this.validateGenericPage();
        driver.getCurrentUrl().contains("http://practice.automationtesting.in/checkout/order-received/");
    }

    public void validateOrderBeenGenerated(){
        waiter.waitForElement(orderMessage);
        waiter.waitForElement(orderNumber);
        String message = driver.findElement(orderMessage).getText();
        String number = driver.findElement(orderNumber).findElement(By.tagName("strong")).getText();


        assert "Thank you. Your order has been received.".equals(message);
        assert number.length() == 4;
    }

    public void validateItemsAndCartValue(int itemQuantity, double cartValue){
        double actualCartValue = Double.valueOf(driver.findElement(cartSubtotal).findElement(cartSubtotalValue).getText().replaceAll("₹", "").
                replaceAll(",", ""));
        int actualItemQuantity = driver.findElements(cartItems).size();

        assert itemQuantity == actualItemQuantity;
        //assert cartValue == actualCartValue;
    }
}

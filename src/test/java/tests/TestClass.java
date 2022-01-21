package tests;

import helpers.Waiter;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.AccountDetailsPage;
import pageobjects.CartPage;
import pageobjects.HomePage;
import pageobjects.OrderPage;
import java.util.Arrays;
import java.util.List;

public class TestClass {

    private WebDriver driver;
    private Waiter waiter;
    private HomePage homePage;
    private AccountDetailsPage accountDetailsPage;
    private CartPage cartPage;
    private OrderPage orderPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        waiter = new Waiter(driver);

        driver.manage().window().maximize();

        homePage = new HomePage(driver, waiter);
        cartPage = new CartPage(driver, waiter);
        accountDetailsPage = new AccountDetailsPage(driver, waiter);
        orderPage = new OrderPage(driver, waiter);
    }

    @Test
    @Epic("Automation Testing Task")
    @Story("Testing exercise from Upwork")
    public void initialTest() throws InterruptedException {
        driver.get("http://practice.automationtesting.in/");

        homePage.validateHomePage();

        List<String> itemsToAdd = Arrays.asList("Mastering JavaScript", "Selenium Ruby", "Thinking in HTML");
        homePage.validateCartIsEmpty();
        double cartValue = homePage.addItemToBasketAndValidateCart(itemsToAdd);
        homePage.clickOnCartButton();

        cartPage.validateItemsAndCartValue(itemsToAdd.size(), cartValue);
        cartPage.clickOnProceedToCheckout();

        accountDetailsPage.fillPersonalInformation("Ramiro", "Gioia", "Upwork Company",
                "ramiroagioia@testingmachine.com", "+5491123257124", "Argentina", "Veracruz 5555",
                "Buenos Aires", "Buenos Aires", 1822, false);
        accountDetailsPage.validateItemsAndCartValue(itemsToAdd.size(), cartValue);
        accountDetailsPage.clickOnPlaceOrder();

        orderPage.validateOrderPage();
        orderPage.validateOrderBeenGenerated();
        orderPage.validateItemsAndCartValue(itemsToAdd.size(), cartValue);
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}

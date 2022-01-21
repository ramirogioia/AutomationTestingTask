package tests;

import helpers.Waiter;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.AccountDetailsPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public class TestClass {

    private WebDriver driver;
    private Waiter waiter;
    private HomePage homePage;
    private LoginPage loginPage;
    private AccountDetailsPage accountDetailsPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        waiter = new Waiter(driver);

        driver.manage().window().maximize();

        homePage = new HomePage(driver, waiter);
        loginPage = new LoginPage(driver, waiter);
        accountDetailsPage = new AccountDetailsPage(driver, waiter);
    }

    @Test
    @Epic("Automation Testing Task")
    @Story("Testing exercise from Upwork")
    @Step("Checkout Testing")
    public void initialTest() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");

        homePage.validateHomePage();
        homePage.clickOnSignInButton();

        loginPage.validateLoginPage();
        loginPage.validateAndEnterEmailAddress("testing_case3355665545@testing.com");
        loginPage.clickOnCreateAccount();

        accountDetailsPage.validateAccountDetailsPage();
        accountDetailsPage.fillPersonalInformation(true, "Ramiro", "Gioia",
                "testing_case3355665545@testing.com", "testing1234", "4/6/1995",
                false, false);

        Thread.sleep(2000);
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}

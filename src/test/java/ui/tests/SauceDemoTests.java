package ui.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.pages.CheckoutPages;
import ui.pages.InventoryPage;
import ui.pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SauceDemoTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CheckoutPages checkoutPages;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkoutPages = new CheckoutPages(driver);

        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void test01_VerifyProductDetails() {
        String expectedName = inventoryPage.getFirstItemName();
        String expectedPrice = inventoryPage.getFirstItemPrice();

        inventoryPage.clickFirstItemName();

        assertEquals(expectedName, inventoryPage.getProductDetailName());
        assertEquals(expectedPrice, inventoryPage.getProductDetailPrice());
    }

    @Test
    public void test02_VerifyCheckoutTotalCalculation() {
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();
        checkoutPages.startCheckout();
        checkoutPages.fillInformation("Carlos", "Mendoza", "12345");

        double itemTotal = checkoutPages.getItemTotal();
        double tax = checkoutPages.getTax();
        double expectedTotal = itemTotal + tax;

        assertEquals(expectedTotal, checkoutPages.getTotal(), 0.01);
    }

    @Test
    public void test03_SortProductsPriceHighToLow() {
        inventoryPage.selectSortOption("Price (high to low)");
        assertEquals("$49.99", inventoryPage.getFirstItemPrice());
    }

    @Test
    public void test04_VerifyCartItemDetails() {
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();
        assertEquals("Sauce Labs Backpack", inventoryPage.getFirstItemName());
    }

    @Test
    public void test05_CompleteCheckoutFlow() {
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();
        checkoutPages.startCheckout();
        checkoutPages.fillInformation("Carlos", "Mendoza", "12345");
        checkoutPages.finishCheckout();

        assertEquals("Thank you for your order!", checkoutPages.getCompleteMessage());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
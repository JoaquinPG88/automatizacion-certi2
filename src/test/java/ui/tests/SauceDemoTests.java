package ui.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.pages.CheckoutPages;
import ui.pages.InventoryPage;
import ui.pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class SauceDemoTests {
    WebDriver driver;
    InventoryPage inventoryPage;
    CheckoutPages checkoutPages;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        
        // Login previo a cada prueba
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        inventoryPage = new InventoryPage(driver);
        checkoutPages = new CheckoutPages(driver);
    }

    @Test
    public void test01_AddProductToCart() {
        inventoryPage.addBackpack();
        assertEquals("1", inventoryPage.getCartBadgeCount(), "El carrito debería tener 1 ítem");
    }

    @Test
    public void test02_RemoveProductFromInventory() {
        inventoryPage.addBackpack();
        inventoryPage.removeBackpack();
        // Cuando el carrito está vacío, el badge desaparece del DOM
        assertTrue(driver.findElements(org.openqa.selenium.By.cssSelector(".shopping_cart_badge")).isEmpty());
    }

    @Test
    public void test03_SortProductsPriceHighToLow() {
        inventoryPage.sortItemsByPriceHighToLow();
        assertEquals("$49.99", inventoryPage.getFirstItemPrice(), "El primer ítem debería ser el más caro");
    }

    @Test
    public void test04_VerifyCartItemDetails() {
        inventoryPage.addBackpack();
        inventoryPage.goToCart();
        assertEquals("Sauce Labs Backpack", checkoutPages.getCartItemName(), "El nombre del producto no coincide");
    }

    @Test
    public void test05_CompleteCheckoutFlow() {
        inventoryPage.addBackpack();
        inventoryPage.goToCart();
        checkoutPages.fillCheckoutInfoAndFinish("Joaquin", "Peredo", "12345");
        assertEquals("Thank you for your order!", checkoutPages.getSuccessMessage());
    }

    @AfterEach
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
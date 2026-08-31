package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {
    private WebDriver driver;

    private By sortDropdown = By.cssSelector(".product_sort_container");
    private By firstItemName = By.cssSelector(".inventory_item_name");
    private By firstItemPrice = By.cssSelector(".inventory_item_price");
    private By addToCartButton = By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");
    private By removeButton = By.cssSelector("[data-test='remove-sauce-labs-backpack']");
    private By cartBadge = By.cssSelector(".shopping_cart_badge");
    private By cartLink = By.cssSelector(".shopping_cart_link");
    
    // Elementos de la pantalla de detalle
    private By productDetailName = By.cssSelector(".inventory_details_name");
    private By productDetailPrice = By.cssSelector(".inventory_details_price");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectSortOption(String optionText) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(optionText);
    }

    public String getFirstItemName() {
        return driver.findElement(firstItemName).getText();
    }

    public String getFirstItemPrice() {
        return driver.findElement(firstItemPrice).getText();
    }

    public void clickFirstItemName() {
        driver.findElement(firstItemName).click();
    }

    public String getProductDetailName() {
        return driver.findElement(productDetailName).getText();
    }

    public String getProductDetailPrice() {
        return driver.findElement(productDetailPrice).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(addToCartButton).click();
    }

    public String getCartBadgeText() {
        return driver.findElement(cartBadge).getText();
    }

    public void goToCart() {
        driver.findElement(cartLink).click();
    }
}
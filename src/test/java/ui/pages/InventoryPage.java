package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {
    private WebDriver driver;
    
    private By addBackpackBtn = By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");
    private By removeBackpackBtn = By.cssSelector("[data-test='remove-sauce-labs-backpack']");
    private By cartBadge = By.cssSelector(".shopping_cart_badge");
    private By sortDropdown = By.cssSelector(".product_sort_container");
    private By firstItemPrice = By.cssSelector(".inventory_item_price");
    private By cartIcon = By.cssSelector(".shopping_cart_link");

    public InventoryPage(WebDriver driver) { this.driver = driver; }

    public void addBackpack() { driver.findElement(addBackpackBtn).click(); }
    public void removeBackpack() { driver.findElement(removeBackpackBtn).click(); }
    public String getCartBadgeCount() { return driver.findElement(cartBadge).getText(); }
    
    public void sortItemsByPriceHighToLow() {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByValue("hilo");
    }
    
    public String getFirstItemPrice() { return driver.findElements(firstItemPrice).get(0).getText(); }
    public void goToCart() { driver.findElement(cartIcon).click(); }
}
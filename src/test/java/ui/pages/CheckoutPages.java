package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPages {
    private WebDriver driver;
    
    private By checkoutBtn = By.cssSelector("[data-test='checkout']");
    private By firstName = By.cssSelector("[data-test='firstName']");
    private By lastName = By.cssSelector("[data-test='lastName']");
    private By zip = By.cssSelector("[data-test='postalCode']");
    private By continueBtn = By.cssSelector("[data-test='continue']");
    private By finishBtn = By.cssSelector("[data-test='finish']");
    private By successMessage = By.cssSelector(".complete-header");
    private By cartItemName = By.cssSelector(".inventory_item_name");

    public CheckoutPages(WebDriver driver) { this.driver = driver; }

    public String getCartItemName() { return driver.findElement(cartItemName).getText(); }
    
    public void fillCheckoutInfoAndFinish(String fName, String lName, String zCode) {
        driver.findElement(checkoutBtn).click();
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(zip).sendKeys(zCode);
        driver.findElement(continueBtn).click();
        driver.findElement(finishBtn).click();
    }
    
    public String getSuccessMessage() { return driver.findElement(successMessage).getText(); }
}
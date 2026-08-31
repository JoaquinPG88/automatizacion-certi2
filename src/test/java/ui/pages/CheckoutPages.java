package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPages {
    private WebDriver driver;

    private By checkoutButton = By.id("checkout");
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By completeHeader = By.cssSelector(".complete-header");

    // Elementos de desglose de precios
    private By itemTotalLabel = By.cssSelector(".summary_subtotal_label");
    private By taxLabel = By.cssSelector(".summary_tax_label");
    private By totalLabel = By.cssSelector(".summary_total_label");

    public CheckoutPages(WebDriver driver) {
        this.driver = driver;
    }

    public void startCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(postalCode);
        driver.findElement(continueButton).click();
    }

    public double getItemTotal() {
        String text = driver.findElement(itemTotalLabel).getText(); // Ej: "Item total: $29.99"
        return Double.parseDouble(text.replace("Item total: $", ""));
    }

    public double getTax() {
        String text = driver.findElement(taxLabel).getText(); // Ej: "Tax: $2.40"
        return Double.parseDouble(text.replace("Tax: $", ""));
    }

    public double getTotal() {
        String text = driver.findElement(totalLabel).getText(); // Ej: "Total: $32.39"
        return Double.parseDouble(text.replace("Total: $", ""));
    }

    public void finishCheckout() {
        driver.findElement(finishButton).click();
    }

    public String getCompleteMessage() {
        return driver.findElement(completeHeader).getText();
    }
}
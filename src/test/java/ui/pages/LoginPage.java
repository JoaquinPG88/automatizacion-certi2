package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By userField = By.cssSelector("[data-test='username']");
    private By passField = By.cssSelector("[data-test='password']");
    private By loginBtn = By.cssSelector("[data-test='login-button']");

    public LoginPage(WebDriver driver) { this.driver = driver; }

    public void login(String user, String pass) {
        driver.findElement(userField).sendKeys(user);
        driver.findElement(passField).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }
}
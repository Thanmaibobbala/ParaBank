package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By username = By.name("username");
    private By password = By.name("password");
    private By loginBtn = By.xpath("//input[@value='Log In']");
    private By errorMsg = By.xpath("//p[@class='error']");
    private By logoutLink = By.linkText("Log Out");

    // Login action
    public void login(String user, String pass) {

        waitForElement(username);

        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);

        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);

        driver.findElement(loginBtn).click();
    }

    public boolean isErrorDisplayed() {
        try {
            waitForElement(errorMsg);
            return driver.findElement(errorMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoutVisible() {
        try {
            waitForElement(logoutLink);
            return driver.findElement(logoutLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        waitForElement(logoutLink);
        driver.findElement(logoutLink).click();
    }
}
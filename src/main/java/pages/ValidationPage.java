package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ValidationPage extends BasePage {

    public ValidationPage(WebDriver driver) {
        super(driver);
    }

    // 🔹 Register page
    private By registerLink = By.linkText("Register");
    private By registerBtn = By.xpath("//input[@value='Register']");
    private By errorMsg = By.xpath("//span[@class='error']");

    // 🔹 Bill Pay
    private By billPayLink = By.linkText("Bill Pay");
    private By sendBtn = By.xpath("//input[@value='Send Payment']");
    private By billError = By.xpath("//span[@class='error']");

    // 🔹 Actions

    public void openRegister() {
        waitForElement(registerLink);
        driver.findElement(registerLink).click();
    }

    public void submitEmptyRegister() {
        waitForElement(registerBtn);
        driver.findElement(registerBtn).click();
    }

    public boolean isRegisterErrorDisplayed() {
        try {
            waitForElement(errorMsg);
            return driver.findElement(errorMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openBillPay() {
        waitForElement(billPayLink);
        driver.findElement(billPayLink).click();
    }

    public void submitEmptyBillPay() {
        waitForElement(sendBtn);
        driver.findElement(sendBtn).click();
    }

    public boolean isBillPayErrorDisplayed() {
        try {
            waitForElement(billError);
            return driver.findElement(billError).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
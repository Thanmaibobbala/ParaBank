package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By accountsTable = By.id("accountTable");
    private By accountRows = By.xpath("//table[@id='accountTable']/tbody/tr");
    private By accountLinks = By.xpath("//table[@id='accountTable']//a");

    // Verify accounts present
    public boolean isAccountTableDisplayed() {
        waitForElement(accountsTable);
        return driver.findElement(accountsTable).isDisplayed();
    }

    public int getAccountCount() {
        List rows = driver.findElements(accountRows);
        return rows.size();
    }

    // Click first account
    public void clickFirstAccount() {
        waitForElement(accountLinks);
        driver.findElements(accountLinks).get(0).click();
    }

    // Account detail page
    private By accountNumber = By.id("accountId");
    private By accountBalance = By.id("balance");

    public boolean isAccountDetailDisplayed() {
        waitForElement(accountNumber);
        return driver.findElement(accountNumber).isDisplayed();
    }
}
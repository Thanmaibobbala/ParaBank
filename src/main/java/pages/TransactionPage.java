package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransactionPage extends BasePage {

    public TransactionPage(WebDriver driver) {
        super(driver);
    }

    // 🔹 Locators

    // Navigation
    private By findTransactionLink = By.linkText("Find Transactions");

    // Find by Amount section
    private By amountField = By.id("amount");

    // 🔥 Correct button (4th "Find Transactions" button)
    private By findByAmountBtn = By.xpath("(//button[contains(text(),'Find Transactions')])[4]");

    // Result table
    private By resultTable = By.id("transactionTable");

    // No result message
    private By noResultMsg = By.xpath("//p[contains(text(),'No transactions found')]");

    // 🔹 Actions

    public void navigateToFindTransactions() {
        waitForElement(findTransactionLink);
        driver.findElement(findTransactionLink).click();
    }

    public void searchByAmount(String amount) {

        // Wait for amount field
        waitForElement(amountField);

        // Enter amount
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys(amount);

        // Click correct button
        driver.findElement(findByAmountBtn).click();
    }

    // 🔹 Validations

    public boolean isResultDisplayed() {
        try {
            waitForElement(resultTable);
            return driver.findElement(resultTable).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoResultDisplayed() {
        try {
            waitForElement(noResultMsg);
            return driver.findElement(noResultMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
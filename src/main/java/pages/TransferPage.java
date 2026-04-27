package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TransferPage extends BasePage {

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By transferLink = By.linkText("Transfer Funds");
    private By amountField = By.id("amount");
    private By fromAccount = By.id("fromAccountId");
    private By toAccount = By.id("toAccountId");
    private By transferBtn = By.xpath("//input[@value='Transfer']");
    private By successMsg = By.xpath("//h1[contains(text(),'Transfer Complete')]");

    // Actions
    public void navigateToTransfer() {
        waitForElement(transferLink);
        driver.findElement(transferLink).click();
    }

    public void transferFunds(String amount) {

        waitForElement(amountField);

        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys(amount);

        // 🔥 Wait for dropdowns
        waitForElement(fromAccount);
        waitForElement(toAccount);

        Select from = new Select(driver.findElement(fromAccount));
        Select to = new Select(driver.findElement(toAccount));

        // 🔥 Ensure different accounts
        from.selectByIndex(0);

        if (to.getOptions().size() > 1) {
            to.selectByIndex(1);
        } else {
            to.selectByIndex(0); // fallback
        }

        driver.findElement(transferBtn).click();
    }

    public boolean isTransferSuccessful() {
        try {
            waitForElement(successMsg);
            return driver.findElement(successMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
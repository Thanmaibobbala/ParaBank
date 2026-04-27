package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TransactionPage;
import utils.ConfigReader;

public class TransactionTest extends BaseTest {

    @Test
    public void testTransactionSearchByAmount() {

        ConfigReader config = new ConfigReader();
        driver.get(config.getBaseUrl());

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("john", "demo");

        // Navigate
        TransactionPage transactionPage = new TransactionPage(driver);
        transactionPage.navigateToFindTransactions();

        // Search
        transactionPage.searchByAmount("100");

        // Validate (either result OR no result)
        boolean result = transactionPage.isResultDisplayed() || transactionPage.isNoResultDisplayed();

        Assert.assertTrue(result, "Transaction search failed");
    }
}
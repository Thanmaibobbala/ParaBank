package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TransferPage;
import utils.ConfigReader;

public class TransferTest extends BaseTest {

    @Test
    public void testFundTransfer() {

        ConfigReader config = new ConfigReader();
        driver.get(config.getBaseUrl());

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("john", "demo");

        // Transfer
        TransferPage transferPage = new TransferPage(driver);
        transferPage.navigateToTransfer();
        transferPage.transferFunds("100");

        Assert.assertTrue(transferPage.isTransferSuccessful(), "Transfer failed");
    }
}
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ValidationPage;
import utils.ConfigReader;

public class ValidationTest extends BaseTest {

	@Test
	public void testRegisterValidation() {

	    ConfigReader config = new ConfigReader();
	    driver.get(config.getBaseUrl());

	    // 🔥 Ensure logged-out state
	    LoginPage loginPage = new LoginPage(driver);
	    try {
	        loginPage.clickLogout();
	    } catch (Exception e) {
	        // already logged out → ignore
	    }

	    ValidationPage page = new ValidationPage(driver);

	    page.openRegister();
	    page.submitEmptyRegister();

	    Assert.assertTrue(page.isRegisterErrorDisplayed(), "Register validation failed");
	}

    @Test
    public void testBillPayValidation() {

        ConfigReader config = new ConfigReader();
        driver.get(config.getBaseUrl());

        // Login required
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("john", "demo");

        ValidationPage page = new ValidationPage(driver);

        page.openBillPay();
        page.submitEmptyBillPay();

        Assert.assertTrue(page.isBillPayErrorDisplayed(), "Bill Pay validation failed");
    }
}
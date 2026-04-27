package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.AccountPage;
import utils.ConfigReader;

public class AccountTest extends BaseTest {

	@Test
	public void testAccountOverview() {

	    ConfigReader config = new ConfigReader();
	    driver.get(config.getBaseUrl());

	    // 🔥 Login FIRST (important)
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.login("john", "demo");

	    AccountPage accountPage = new AccountPage(driver);

	    Assert.assertTrue(accountPage.isAccountTableDisplayed(), "Account table not visible");

	    int count = accountPage.getAccountCount();
	    Assert.assertTrue(count > 0, "No accounts found");

	    accountPage.clickFirstAccount();

	    Assert.assertTrue(accountPage.isAccountDetailDisplayed(), "Account details not shown");
	}
}
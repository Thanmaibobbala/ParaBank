package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;
import utils.ConfigReader;

public class AuthTest extends BaseTest {

    @Test(priority = 1)
    public void registerUser() {

        String username = "user" + System.currentTimeMillis();
        String password = "pass123";

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerUser(username, password);

        // logout after register
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogout();

        Assert.assertTrue(driver.getTitle().contains("ParaBank"));
    }

    // ✅ DataProvider
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"john", "demo", true},
                {"wrongUser", "wrongPass", false}
        };
    }

    @Test(priority = 2, dataProvider = "loginData")
    public void testLogin(String username, String password, boolean expected) {

        LoginPage loginPage = new LoginPage(driver);

        ConfigReader config = new ConfigReader();
        driver.get(config.getBaseUrl());

        loginPage.login(username, password);

        if (expected) {
            Assert.assertTrue(loginPage.isLogoutVisible(), "Valid login failed");
            loginPage.clickLogout();
        } else {
            // 🔥 FORCE FAIL to capture screenshot
            Assert.fail("Forcing failure for screenshot demo");
        }
    }
}
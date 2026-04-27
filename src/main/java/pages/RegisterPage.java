package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private By registerLink = By.linkText("Register");

    private By firstName = By.id("customer.firstName");
    private By lastName = By.id("customer.lastName");
    private By address = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zip = By.id("customer.address.zipCode");
    private By phone = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");

    private By username = By.id("customer.username");
    private By password = By.id("customer.password");
    private By confirmPassword = By.id("repeatedPassword");

    private By registerBtn = By.xpath("//input[@value='Register']");

    public void registerUser(String user, String pass) {

        driver.findElement(registerLink).click();

        driver.findElement(firstName).sendKeys("Hema");
        driver.findElement(lastName).sendKeys("K");
        driver.findElement(address).sendKeys("Test Street");
        driver.findElement(city).sendKeys("Vijayawada");
        driver.findElement(state).sendKeys("AP");
        driver.findElement(zip).sendKeys("520001");
        driver.findElement(phone).sendKeys("9999999999");
        driver.findElement(ssn).sendKeys("1234");

        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirmPassword).sendKeys(pass);

        driver.findElement(registerBtn).click();
    }
}
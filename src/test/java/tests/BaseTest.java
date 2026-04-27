package tests;

import base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.initDriver();
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
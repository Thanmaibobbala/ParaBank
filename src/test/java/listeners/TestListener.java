package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.*;

import base.DriverFactory;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getReport();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail("Test Failed");

        String path = ScreenshotUtil.captureScreenshot(
                DriverFactory.getDriver(),
                result.getName()
        );

        test.addScreenCaptureFromPath(path);
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }
}
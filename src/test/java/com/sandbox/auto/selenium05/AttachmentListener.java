package com.sandbox.auto.selenium05;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AttachmentListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult itestresult) {
        WebDriver driver = DriverSingleton.getDriver();
        Throwable throwable = itestresult.getThrowable();
        if (throwable instanceof AssertionError) {
            saveScreenshot(driver);
        }
    }

    @Attachment(type = "image/png", fileExtension = ".png")
    public static byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

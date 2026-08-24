package com.sandbox.auto.selenium04.tests;

import com.sandbox.auto.selenium04.steps.Exercise1Steps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class TestFail extends WebBaseTests {
    private int expectedImagesNumber = 6;
    private String expectedFrameButtonText = "Frame Butter";

    @Test(testName = "Test Fail")
    @Feature("Homework 4 Allure report and Steps design")
    @Story("Screenshot listener test")
    public void testFail() {
        Exercise1Steps ex1Steps = new Exercise1Steps(webDriver);
        //Step Fail: Assert that there are 6 images on the Index Page
        ex1Steps.checkImagesDisplayed(expectedImagesNumber);

        //Step 9: Switch to the iframe and check that there is “Frame Button” in the iframe
        ex1Steps.checkFrameButtonInIFrame(expectedFrameButtonText);

        ex1Steps.getSoftAssert().assertAll();
    }
}

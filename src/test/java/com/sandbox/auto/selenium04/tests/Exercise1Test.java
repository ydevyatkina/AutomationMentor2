package com.sandbox.auto.selenium04.tests;

import com.sandbox.auto.selenium04.enums.MenuTexts;
import com.sandbox.auto.selenium04.steps.Exercise1Steps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class Exercise1Test extends WebBaseTests {
    private List<String> expectedIconTexts = Arrays
            .asList("To include good practices\nand ideas from successful\nEPAM project",
                    "To be flexible and\ncustomizable",
                    "To be multiplatform",
                    "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
    private String expectedFrameButtonText = "Frame Button";
    private int expectedHeaderItems = 4;
    private int expectedImagesNumber = 4;
    private int expectedTextsNumber = 4;
    private int expectedLeftSectionItems = 5;

    @Test(testName = "Exercise 1")
    @Feature("Homework 4 Allure report and Steps design")
    @Story("Home page tests")
    public void exercise1() {
        Exercise1Steps ex1Steps = new Exercise1Steps(webDriver);
        //Step 5: Assert that there are 4 items on the header section are displayed, and they have proper texts
        //"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
        ex1Steps.checkHeaderItems(expectedHeaderItems, MenuTexts.getUpperMenuTexts());

        //Step 6: Assert that there are 4 images on the Index Page, and they are displayed
        ex1Steps.checkImagesDisplayed(expectedImagesNumber);

        //Step 7: Assert that there are 4 texts on the Index Page under icons, and they have proper text
        ex1Steps.checkTextUnderIcons(expectedTextsNumber, expectedIconTexts);

        //Step 8: Assert that there is the iframe with “Frame Button” exist
        ex1Steps.checkIframeIsDisplayed();

        //Step 9: Switch to the iframe and check that there is “Frame Button” in the iframe
        ex1Steps.checkFrameButtonInIFrame(expectedFrameButtonText);

        //Step 10: Switch to original window back
        ex1Steps.switchToDefaultContent();

        //Step 11: Assert that there are 5 items in the Left Section are displayed, and they have proper text
        // “Home”, “Contact form”, “Service”, “Metals & Colors”, “Elements packs”
        ex1Steps.checkLeftSectionItems(expectedLeftSectionItems, MenuTexts.getLeftMenuTexts());

        ex1Steps.getSoftAssert().assertAll();
    }
}

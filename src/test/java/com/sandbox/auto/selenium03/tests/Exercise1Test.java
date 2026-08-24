package com.sandbox.auto.selenium03.tests;

import com.sandbox.auto.selenium03.HomePage;
import com.sandbox.auto.selenium03.enums.MenuTexts;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Epic("Selenium 4 Exercises")
@Feature("Exercise 1")

public class Exercise1Test extends WebBaseTests {
    private HomePage homePage;
    private List<String> expectedIconTexts = Arrays
            .asList("To include good practices\nand ideas from successful\nEPAM project",
            "To be flexible and\ncustomizable",
            "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
    private String expectedFrameButtonText = "Frame Button";

    @Test(testName = "Exercise 1")
    @Description("Testing of Home Page desc")
    @Story("Exersice 1 Test")
    public void exercise1() {
        step("Step 5: Assert that there are 4 items on the header section are displayed, "
                + "and they have proper texts \"HOME\", \"CONTACT FORM\", \"SERVICE\", \"METALS & COLORS\"");
        homePage = new HomePage(webDriver);
        softAssert.assertEquals(homePage.getMenuElements().size(), 4);

        for (WebElement menuItem : homePage.getMenuElements()) {
            softAssert.assertTrue(menuItem.isDisplayed());
        }
        softAssert.assertEquals(homePage.getMenuTexts(), MenuTexts.getUpperMenuTexts());

        step("Step 6: Assert that there are 4 images on the Index Page, and they are displayed");
        softAssert.assertEquals(homePage.getIndexImages().size(), 4);

        for (WebElement indexImage : homePage.getIndexImages()) {
            softAssert.assertTrue(indexImage.isDisplayed());
        }

        step("Step 7: Assert that there are 4 texts on the Index Page under icons, and they have proper text");
        softAssert.assertEquals(homePage.getIndexImagesTextElements().size(), 4);

        softAssert.assertEquals(homePage.getIndexImagesTexts(), expectedIconTexts);

        step("Step 8: Assert that there is the iframe with “Frame Button” exist");
        softAssert.assertTrue(homePage.getFirstFrame().isDisplayed());

        step("Step 9: Switch to the iframe and check that there is “Frame Button” in the iframe");
        homePage.switchToFrame();
        softAssert.assertEquals(homePage.getFrameButtonValue(), expectedFrameButtonText);

        step("Step 10: Switch to original window back");
        homePage.switchToDefault();

        step("Step 11: Assert that there are 5 items in the Left Section are displayed, "
                + "and they have proper text “Home”, “Contact form”, “Service”, “Metals & Colors”, “Elements packs”");
        softAssert.assertEquals(homePage.getLeftMenuElements().size(), 5);

        for (WebElement leftMenuItem : homePage.getLeftMenuElements()) {
            softAssert.assertTrue(leftMenuItem.isDisplayed());
        }
        softAssert.assertEquals(homePage.getLeftMenuTexts(), MenuTexts.getLeftMenuTexts());

        softAssert.assertAll();
    }
}

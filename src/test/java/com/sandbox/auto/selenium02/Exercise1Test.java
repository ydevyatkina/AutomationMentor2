package com.sandbox.auto.selenium02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise1Test extends WebBaseTests {
    private List<String> iconTexts;
    private String firstFrameId = "frame";
    private String frameButtonId = "frame-button";
    private String frameButtonText = "Frame Button";

    public void setup() {
        iconTexts = Arrays.asList("To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
    }

    @Test(testName = "Exercise 1")
    public void exercise1() {
        openUrl();
        login();
        setup();
        //Step 5: Assert that there are 4 items on the header section are displayed, and they have proper texts
        //"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
        webDriver.switchTo().defaultContent();
        List<WebElement> menuElements = webDriver.findElements(By.cssSelector("ul.m-l8 > li > a"));
        softAssert.assertEquals(menuElements.size(), 4);

        List<String> actualMenuTexts = new ArrayList<>();
        for (WebElement menuItem : menuElements) {
            actualMenuTexts.add(menuItem.getText());
            softAssert.assertTrue(menuItem.isDisplayed());
        }
        softAssert.assertEquals(actualMenuTexts, MenuTexts.getUpperMenuTexts());

        //Step 6: Assert that there are 4 images on the Index Page, and they are displayed
        List<WebElement> indexImages = webDriver.findElements(By.cssSelector(".icons-benefit"));
        softAssert.assertEquals(indexImages.size(), 4);

        for (WebElement indexImage : indexImages) {
            softAssert.assertTrue(indexImage.isDisplayed());
        }

        //Step 7: Assert that there are 4 texts on the Index Page under icons, and they have proper text
        List<WebElement> indexImagesTexts = webDriver.findElements(By.cssSelector(".benefit-txt"));
        softAssert.assertEquals(indexImagesTexts.size(), 4);

        List<String> actualIconTexts = new ArrayList<>();
        for (WebElement indexImageText : indexImagesTexts) {
            actualIconTexts.add(indexImageText.getText());
        }
        softAssert.assertEquals(actualIconTexts, iconTexts);

        //Step 8: Assert that there is the iframe with “Frame Button” exist
        List<WebElement> frameElements = webDriver.findElements(By.id(firstFrameId));
        softAssert.assertTrue(frameElements.size() > 0);

        //Step 9: Switch to the iframe and check that there is “Frame Button” in the iframe
        WebElement iframe = webDriver.findElement(By.id(firstFrameId));
        webDriver.switchTo().frame(iframe);
        String buttonName = webDriver.findElement(By.id(frameButtonId)).getAttribute("value");
        softAssert.assertEquals(buttonName, frameButtonText);

        //Step 10: Switch to original window back
        webDriver.switchTo().defaultContent();

        //Step 11: Assert that there are 5 items in the Left Section are displayed, and they have proper text
        // “Home”, “Contact form”, “Service”, “Metals & Colors”, “Elements packs”
        List<WebElement> leftMenuElements = webDriver
                .findElements(By.cssSelector("ul.sidebar-menu.left > li > a > span"));
        softAssert.assertEquals(leftMenuElements.size(), 5);

        List<String> actualLeftMenuTexts = new ArrayList<>();
        for (WebElement leftMenuItem : leftMenuElements) {
            actualLeftMenuTexts.add(leftMenuItem.getText().toUpperCase());
            softAssert.assertTrue(leftMenuItem.isDisplayed());
        }
        softAssert.assertEquals(actualLeftMenuTexts, MenuTexts.getLeftMenuTexts());

        softAssert.assertAll();
    }
}

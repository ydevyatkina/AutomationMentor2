package com.sandbox.auto.selenium04.steps;

import com.sandbox.auto.selenium03.HomePage;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Exercise1Steps extends WebBaseSteps {
    private HomePage homePage;

    public Exercise1Steps(WebDriver driver) {
        super.setUp(driver);
        homePage = new HomePage(driver);
    }

    @Step("Step 5: Assert that there are 4 items on the header section are displayed, and they have proper texts"
            + "\n'HOME', 'CONTACT FORM', 'SERVICE', 'METALS & COLORS'")
    public void checkHeaderItems(int expItemsNumber, List<String> expUpperMenuTexts) {
        softAssert.assertThat(homePage.getMenuElements().size()).isEqualTo(expItemsNumber);

        for (WebElement menuItem : homePage.getMenuElements()) {
            softAssert.assertThat(menuItem.isDisplayed()).isTrue();
        }
        softAssert.assertThat(homePage.getMenuTexts()).isEqualTo(expUpperMenuTexts);
    }

    @Step("Step 6: Assert that there are 4 images on the Index Page, and they are displayed")
    public void checkImagesDisplayed(int expectedImagesNumber) {
        softAssert.assertThat(homePage.getIndexImages().size()).isEqualTo(expectedImagesNumber);


        for (WebElement indexImage : homePage.getIndexImages()) {
            softAssert.assertThat(indexImage.isDisplayed()).isTrue();
        }
    }

    @Step("Step 7: Assert that there are 4 texts on the Index Page under icons, and they have proper text")
    public void checkTextUnderIcons(int textNumber, List<String> expectedIconTexts) {
        softAssert.assertThat(homePage.getIndexImagesTextElements().size()).isEqualTo(textNumber);

        softAssert.assertThat(homePage.getIndexImagesTexts()).isEqualTo(expectedIconTexts);
    }

    @Step("Step 8: Assert that there is the iframe with “Frame Button” exist")
    public void checkIframeIsDisplayed() {
        softAssert.assertThat(homePage.getFirstFrame().isDisplayed()).isTrue();
    }

    @Step("Step 9: Switch to the iframe and check that there is “Frame Button” in the iframe")
    public void checkFrameButtonInIFrame(String expectedBtnName) {
        homePage.switchToFrame();
        softAssert.assertThat(homePage.getFrameButtonValue()).isEqualTo(expectedBtnName);
    }

    @Step("Step 10: Switch to original window back")
    public void switchToDefaultContent() {
        homePage.switchToDefault();
    }

    @Step("Step 11: Assert that there are 5 items in the Left Section are displayed, and they have proper text"
            + "\n“Home”, “Contact form”, “Service”, “Metals & Colors”, “Elements packs”")
    public void checkLeftSectionItems(int expectedItemsNumber, List<String> expectedMenuText) {
        softAssert.assertThat(homePage.getLeftMenuElements().size()).isEqualTo(expectedItemsNumber);

        for (WebElement leftMenuItem : homePage.getLeftMenuElements()) {
            softAssert.assertThat(leftMenuItem.isDisplayed()).isTrue();
        }
        softAssert.assertThat(homePage.getLeftMenuTexts()).isEqualTo(expectedMenuText);
    }

    public SoftAssertions getSoftAssert() {
        return softAssert;
    }
}

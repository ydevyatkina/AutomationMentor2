package com.sandbox.auto.selenium04.tests;

import com.sandbox.auto.selenium03.enums.DropdownOptionsTexts;
import com.sandbox.auto.selenium04.steps.Exercise2Steps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class Exercise2Test extends WebBaseTests {
    private String expectedDifferentElementsMenuText = "Different elements";

    @Test(testName = "Exercise 2")
    @Feature("Homework 4 Allure report and Steps design")
    @Story("Different Elements page tests")
    public void exercise2() {
        Exercise2Steps ex2Steps = new Exercise2Steps(webDriver);
        //Step 5: Open through the header menu Service -> Different Elements Page
        ex2Steps.openDiffElPageCheckTitle(expectedDifferentElementsMenuText);

        //Step 6: Select checkboxes: Water, Wind
        ex2Steps.selectWaterWindCheckSelection();

        //Step 7: Select radio: Selen
        ex2Steps.selectSelenCheckSelection();

        //Step 8: Select in dropdown Yellow
        ex2Steps.selectYellowCheckSelection(DropdownOptionsTexts.YELLOW.getText());

        //Step 9: Assert that
        // for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        // for radio button there is a log row and value is corresponded to the status of radio button
        // for dropdown there is a log row and value is corresponded to the selected value
        ex2Steps.performDropRadioBoxActionsCheckLogs();
    }

}

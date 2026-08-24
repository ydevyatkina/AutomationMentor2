package com.sandbox.auto.selenium05.cucumber.steps;

import com.sandbox.auto.selenium05.enums.CheckboxesTexts;
import com.sandbox.auto.selenium05.enums.DropdownOptionsTexts;
import com.sandbox.auto.selenium05.enums.ElementTypes;
import com.sandbox.auto.selenium05.enums.RadioButtonsTexts;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class DiffElementsPageSteps extends AbstractBaseStep {
    @When("I navigate to Service -> Different Elements Page through the header menu")
    public void navigateToDifferentElementsPage() {
        homePage.openDifferentElementsPage();
    }

    @When("I select the checkboxes Water and Wind")
    public void selectCheckBoxes() {
        differentElementsPage.selectItem("Water");
        differentElementsPage.selectItem("Wind");
    }

    @When("I select the radio button Selen")
    public void selectRadioButton() {
        differentElementsPage.selectItem("Selen");
    }

    @When("I select the Yellow element in the dropdown")
    public void selectDropdownOption() {
        differentElementsPage.selectDropdownOption("Yellow");
    }

    @Then("there should be log rows for each interaction with corresponding values")
    public void assertLogRows() {
        getLogsAndVerifyActions(CheckboxesTexts.getCheckboxesTexts(), ElementTypes.CHECKBOX);
        getLogsAndVerifyActions(RadioButtonsTexts.getRadioButtonsTexts(), ElementTypes.RADIOBUTTON);
        getLogsAndVerifyActions(DropdownOptionsTexts.getDropdownOptionsTexts(), ElementTypes.DROPDOWN);
    }

    public void getLogsAndVerifyActions(List<String> items, ElementTypes elementType) {

        for (String item : items) {
            StringBuilder logEntryText = new StringBuilder(item);

            switch (elementType) {
                case CHECKBOX -> {
                    differentElementsPage.selectItem(item);
                    logEntryText.append(": condition changed to ")
                            .append(differentElementsPage
                                    .findButtonElementByText(item)
                                    .isSelected() ? "true" : "false");
                }
                case RADIOBUTTON -> differentElementsPage.selectItem(item);
                case DROPDOWN -> differentElementsPage.selectDropdownOption(item);
                default -> throw new IllegalArgumentException("Unsupported element type: " + elementType);
            }
            assertTrue(differentElementsPage.getLogRecord().stream()
                    .anyMatch(record -> record.getText().contains(logEntryText)));
        }
    }

}

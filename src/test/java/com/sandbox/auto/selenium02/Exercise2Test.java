package com.sandbox.auto.selenium02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Exercise2Test extends WebBaseTests {
    private String differentElementsMenuText = "Different elements";

    public WebElement findMenuItemByText(String menuItemText) {
        return webDriver.findElement(By.xpath("//ul[@role='menu']/li[contains(a, '"
                + menuItemText + "')]"));
    }

    public WebElement findButtonElementByText(String elementItemText) {
        return webDriver.findElement(By.xpath("//label[contains(., '"
                + elementItemText + "')]//input"));
    }

    public void findClickAssertElementByTitle(String elementTitle) {
        WebElement webElement = findButtonElementByText(elementTitle);
        webElement.click();
        assertTrue(webElement.isSelected());
    }

    @Test(testName = "Exercise 2")
    public void exercise2() {
        openUrl();
        login();
        //Step 5: Open through the header menu Service -> Different Elements Page
        WebElement menuDropdown = webDriver.findElement(By.cssSelector("ul.m-l8 > li.dropdown"));
        menuDropdown.click();
        WebElement differentElementMenu = findMenuItemByText(differentElementsMenuText);
        differentElementMenu.click();
        assertEquals(webDriver.getTitle().toLowerCase(), differentElementsMenuText.toLowerCase());

        //Step 6: Select checkboxes: Water, Wind
        findClickAssertElementByTitle(CheckboxesTexts.WATER.getText());
        findClickAssertElementByTitle(CheckboxesTexts.WIND.getText());

        //Step 7: Select radio: Selen
        findClickAssertElementByTitle(RadioButtonsTexts.SELEN.getText());

        //Step 8: Select in dropdown Yellow
        WebElement dropdown = webDriver.findElement(By.cssSelector("select.uui-form-element"));

        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByVisibleText(DropdownOptionsTexts.YELLOW.getText());
        assertEquals(dropdownSelect.getFirstSelectedOption().getText(), DropdownOptionsTexts.YELLOW.getText());

        //Step 9: Assert that
        // for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        WebElement logBox = webDriver.findElement(By.cssSelector(".logs"));

        assertActionInLog(CheckboxesTexts.getCheckboxesTexts(), ElementTypes.CHECKBOX, logBox);

        // for radio button there is a log row and value is corresponded to the status of radio button
        assertActionInLog(RadioButtonsTexts.getRadioButtonsTexts(), ElementTypes.RADIOBUTTON, logBox);

        // for dropdown there is a log row and value is corresponded to the selected value
        assertActionInLog(DropdownOptionsTexts.getDropdownOptionsTexts(), ElementTypes.DROPDOWN, logBox);
    }

    public void findElementAndClick(String item) {
        WebElement element = findButtonElementByText(item);
        element.click();
    }

    public void assertActionInLog(List<String> items, ElementTypes elementType, WebElement logBox) {
        for (String item : items) {
            StringBuilder logEntryText = new StringBuilder(item);

            switch (elementType) {
                case CHECKBOX -> {
                    findElementAndClick(item);
                    logEntryText.append(": condition changed to ")
                            .append(findButtonElementByText(item).isSelected() ? "true" : "false");
                }
                case RADIOBUTTON -> findElementAndClick(item);
                case DROPDOWN -> {
                    WebElement dropdown = webDriver.findElement(By.cssSelector("select.uui-form-element"));
                    Select dropdownSelect = new Select(dropdown);
                    dropdownSelect.selectByVisibleText(item);
                }
                default -> throw new IllegalArgumentException("Unsupported element type: " + elementType);
            }

            WebElement logEntry = logBox.findElement(By.xpath("//li[contains(., '" + logEntryText + "')]"));
            assertTrue(logEntry.isDisplayed());
        }
    }

    public enum ElementTypes {
        CHECKBOX,
        RADIOBUTTON,
        DROPDOWN;
    }
}

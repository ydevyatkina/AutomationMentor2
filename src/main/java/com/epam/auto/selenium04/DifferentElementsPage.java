package com.epam.auto.selenium04;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DifferentElementsPage extends BasePage {
    @FindBy(css = "select.uui-form-element")
    private WebElement dropdown;
    @FindBy(css = ".logs li")
    private List<WebElement> logRecords;

    public DifferentElementsPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WebElement findButtonElementByText(String itemTitle) {
        return webDriver.findElement(By.xpath("//label[contains(., '"
                + itemTitle + "')]//input"));
    }

    public void selectItem(String itemTitle) {
        findButtonElementByText(itemTitle).click();
    }

    private Select getDropdownSelect() {
        return new Select(dropdown);
    }

    public void selectDropdownOption(String optionText) {
        getDropdownSelect().selectByVisibleText(optionText);
    }

    public String getSelectedDropdownOptionText() {
        return getDropdownSelect().getFirstSelectedOption().getText();
    }

    public List<WebElement> getLogRecord() {
        return logRecords;
    }

   public static void printer (String text) {
       System.out.println(text);
   }

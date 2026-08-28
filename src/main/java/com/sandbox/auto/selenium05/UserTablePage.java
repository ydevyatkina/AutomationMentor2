package com.sandbox.auto.selenium05;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserTablePage extends BasePage {
    @FindBy(css = "td select")
    private List<WebElement> dropDown;

    @FindBy(css = "td a")
    private List<WebElement> users;

    @FindBy(css = ".user-description span")
    private List<WebElement> descriptions;

    @FindBy(css = ".user-descr input[type='checkbox']")
    private List<WebElement> checkboxes;

    @FindBy(css = "tbody tr")
    private List<WebElement> tableRow;

    @FindBy(css = ".logs li")
    private List<WebElement> logRecords;

    public UserTablePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public int countDropdowns() {
        return dropDown.size();
    }

    public int countUsers() {
        return users.size();
    }

    public int countDescriptions() {
        return descriptions.size();
    }

    public int countCheckboxes() {
        return checkboxes.size();
    }

    public List<List<String>> getUserTableData() {
        List<List<String>> tableData = new ArrayList<>();
        for (WebElement row : tableRow) {
            List<String> innerList = new ArrayList<>();
            String number = row.findElement(By.tagName("td")).getText();
            String name = row.findElement(By.cssSelector("td a")).getText();
            String description = row.findElement(By.cssSelector("div.user-descr span"))
                    .getText()
                    .replace("\n", " ");
            Collections.addAll(innerList, number, name, description);
            tableData.add(innerList);
        }
        return tableData;
    }

    public List<String> getDropdownValuesForUser(String userName) {
        WebElement userRow = webDriver
                .findElement(By.xpath("//tr/td[./a[contains(text(),'" + userName + "')]]/.."));
        WebElement dropdown = userRow.findElement(By.tagName("select"));
        List<WebElement> options = dropdown.findElements(By.tagName("option"));
        return options.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void selectVipCheckboxForUser(String userName) {
        WebElement userRow = webDriver
                .findElement(By.xpath("//tr[./td[*[contains(text(),'" + userName + "')]]]"));
        WebElement vipCheckbox = userRow.findElement(By.cssSelector("input[type='checkbox']"));
        vipCheckbox.click();
    }

    public List<String> getLogRecords() {
        return logRecords.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}

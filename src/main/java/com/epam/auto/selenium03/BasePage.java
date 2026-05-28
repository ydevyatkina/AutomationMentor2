package com.epam.auto.selenium03;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BasePage {

    protected WebDriver webDriver;
    @FindBy(css = "ul.m-l8 > li > a")
    private List<WebElement> menuElements;
    @FindBy(css = "ul.sidebar-menu.left > li > a > span")
    private List<WebElement> leftMenuElements;
    @FindBy(linkText = "SERVICE")
    private WebElement serviceButton;

    @FindBy(linkText = "DIFFERENT ELEMENTS")
    private WebElement differentElementsButton;

    protected BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public List<WebElement> getMenuElements() {
        return menuElements;
    }

    public List<String> getMenuTexts() {
        List<String> menuTexts = new ArrayList<>();
        for (WebElement menuItem : getMenuElements()) {
            menuTexts.add(menuItem.getText());
        }
        System.out.println(menuTexts);
        return menuTexts;
    }

    public void switchToDefault() {
        webDriver.switchTo().defaultContent();
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public List<WebElement> getLeftMenuElements() {
        return leftMenuElements;
    }

    public List<String> getLeftMenuTexts() {
        List<String> menuTexts = new ArrayList<>();
        for (WebElement menuItem : getLeftMenuElements()) {
            menuTexts.add(menuItem.getText().toUpperCase());
        }
        return menuTexts;
    }

    public void openDifferentElementsPage() {
        serviceButton.click();
        differentElementsButton.click();
    }
}

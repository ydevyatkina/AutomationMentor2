package com.epam.auto.selenium04;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    @FindBy(id = "user-icon")
    private WebElement userIcon;
    @FindBy(id = "strange_name")
    private WebElement strangeName;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    @FindBy(id = "user-name")
    private WebElement userName;
    @FindBy(css = ".icons-benefit")
    private List<WebElement> indexImages;
    @FindBy(css = ".benefit-txt")
    private List<WebElement> indexImagesTexts;
    @FindBy(id = "frame")
    private WebElement firstFrame;
    @FindBy(id = "frame-button")
    private WebElement frameButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String userPassword) {
        userIcon.click();
        strangeName.sendKeys(username);
        password.sendKeys(userPassword);
        loginButton.click();
    }

    public String getUserName() {
        return userName.getText();
    }

    public List<WebElement> getIndexImages() {
        return indexImages;
    }

    public List<WebElement> getIndexImagesTextElements() {
        return indexImagesTexts;
    }

    public List<String> getIndexImagesTexts() {
        System.out.println("getIndexImagesTexts");
        List<String> imagesTexts = new ArrayList<>();
        for (WebElement menuItem : getIndexImagesTextElements()) {
            imagesTexts.add(menuItem.getText());
        }
        return imagesTexts;
    }

    public WebElement getFirstFrame() {
        return firstFrame;
    }

    public void switchToFrame() {
        webDriver.switchTo().frame(getFirstFrame());
    }

    public String getFrameButtonValue() {
        return frameButton.getAttribute("value");
    }
}

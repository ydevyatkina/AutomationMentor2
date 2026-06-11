package com.epam.auto.selenium03;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(id = "user-icon")
    private WebElement userIcon;
    @FindBy(id = "name")
    private WebElement name;
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
        name.sendKeys(username);
        password.sendKeys(userPassword);
        loginButton.click();
        System.out.println("Login Successful");
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

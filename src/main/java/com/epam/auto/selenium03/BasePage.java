package com.epam.auto.selenium03;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * BasePage represents the foundational abstract class for Page Objects.
 * It encapsulates common Selenium WebDriver interactions, web element locators,
 * navigation routines, explicit waits, and utility methods across the test suite.
 */
public abstract class BasePage {

    protected WebDriver webDriver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor jsExecutor;

    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    @FindBy(css = "ul.m-l8 > li > a")
    private List<WebElement> menuElements;

    @FindBy(css = "ul.sidebar-menu.left > li > a > span")
    private List<WebElement> leftMenuElements;

    @FindBy(linkText = "SERVICE")
    private WebElement serviceButton;

    @FindBy(linkText = "DIFFERENT ELEMENTS")
    private WebElement differentElementsButton;

    @FindBy(css = "header")
    private WebElement headerSection;

    @FindBy(css = "footer")
    private WebElement footerSection;

    @FindBy(id = "user-icon")
    private WebElement userIcon;

    @FindBy(id = "name")
    private WebElement loginNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginSubmitButton;

    @FindBy(id = "user-name")
    private WebElement userNameText;

    /**
     * Constructor initializing the WebDriver and common helper wrappers.
     *
     * @param webDriver the active WebDriver instance
     */
    protected BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        this.actions = new Actions(webDriver);
        if (webDriver instanceof JavascriptExecutor) {
            this.jsExecutor = (JavascriptExecutor) webDriver;
        }
    }

    /**
     * Retrieves the top navigation menu elements.
     *
     * @return list of menu WebElements
     */
    public List<WebElement> getMenuElements() {
        return menuElements;
    }

    /**
     * Extracts visible text from top navigation menu items.
     *
     * @return list of strings representing menu labels
     */
    public List<String> getMenuTexts() {
        System.out.println("Hi");
        List<String> menuTexts = new ArrayList<>();
        for (WebElement menuItem : getMenuElements()) {
            menuTexts.add(menuItem.getText());
        }
        return menuTexts;
    }

    /**
     * Switches WebDriver focus back to the default document content.
     */
    public void switchToDefault() {
        webDriver.switchTo().defaultContent();
    }

    /**
     * Retrieves the current page title.
     *
     * @return the page title string
     */
    public String getTitle() {
        return webDriver.getTitle();
    }

    /**
     * Retrieves the left sidebar menu elements.
     *
     * @return list of sidebar menu WebElements
     */
    public List<WebElement> getLeftMenuElements() {
        return leftMenuElements;
    }

    /**
     * Extracts uppercase text values from left sidebar menu elements.
     *
     * @return list of uppercase menu label strings
     */
    public List<String> getLeftMenuTexts() {
        List<String> menuTexts = new ArrayList<>();
        for (WebElement menuItem : getLeftMenuElements()) {
            menuTexts.add(menuItem.getText().toUpperCase());
        }
        return menuTexts;
    }

    /**
     * Navigates to the Different Elements sub-page via top Service dropdown.
     */
    public void openDifferentElementsPage() {
        serviceButton.click();
        differentElementsButton.click();
        System.out.println("Hello");
    }

    /**
     * Clicks on the user icon to open the authentication form.
     */
    public void openLoginForm() {
        waitForElementToBeClickable(userIcon);
        userIcon.click();
    }

    /**
     * Performs standard login operation with the provided credentials.
     *
     * @param username username string
     * @param password password string
     */
    public void login(String username, String password) {
        openLoginForm();
        loginNameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginSubmitButton.click();
    }

    /**
     * Gets the logged-in user display name.
     *
     * @return the user name text
     */
    public String getLoggedInUserName() {
        waitForElementVisibility(userNameText);
        return userNameText.getText();
    }

    /**
     * Checks whether the user is logged in by verifying the username display.
     *
     * @return true if username is displayed, false otherwise
     */
    public boolean isUserLoggedIn() {
        return isElementDisplayed(userNameText);
    }

    /**
     * Waits until the specified element is visible on the page.
     *
     * @param element the WebElement to wait for
     * @return the visible WebElement
     */
    public WebElement waitForElementVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the element located by By is visible on the page.
     *
     * @param locator By locator
     * @return the visible WebElement
     */
    public WebElement waitForElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the specified element becomes clickable.
     *
     * @param element the WebElement to wait for
     * @return the clickable WebElement
     */
    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until the element located by By becomes clickable.
     *
     * @param locator By locator
     * @return the clickable WebElement
     */
    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for an element to be invisible or absent from the DOM.
     *
     * @param locator By locator
     * @return true if element is invisible, false otherwise
     */
    public boolean waitForElementInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Checks if a web element is displayed without throwing NoSuchElementException.
     *
     * @param element the WebElement to check
     * @return true if element is displayed, false otherwise
     */
    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if an element located by By is present and displayed on the page.
     *
     * @param locator By locator
     * @return true if present and displayed, false otherwise
     */
    public boolean isElementDisplayed(By locator) {
        try {
            return webDriver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Scrolls the browser window so that the target element becomes visible.
     *
     * @param element the WebElement to scroll into view
     */
    public void scrollToElement(WebElement element) {
        if (jsExecutor != null) {
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }

    /**
     * Performs a JavaScript click on the given element.
     *
     * @param element the WebElement to click via JS
     */
    public void clickViaJs(WebElement element) {
        if (jsExecutor != null) {
            jsExecutor.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Moves mouse hover over the specified element.
     *
     * @param element target WebElement for mouse hover
     */
    public void hoverOverElement(WebElement element) {
        waitForElementVisibility(element);
        actions.moveToElement(element).perform();
    }

    /**
     * Performs double click on the specified element.
     *
     * @param element target WebElement for double click
     */
    public void doubleClick(WebElement element) {
        waitForElementToBeClickable(element);
        actions.doubleClick(element).perform();
    }

    /**
     * Performs right-click (context click) on the specified element.
     *
     * @param element target WebElement for context click
     */
    public void rightClick(WebElement element) {
        waitForElementToBeClickable(element);
        actions.contextClick(element).perform();
    }

    /**
     * Drags source element and drops it onto the target element.
     *
     * @param source source WebElement
     * @param target target WebElement
     */
    public void dragAndDrop(WebElement source, WebElement target) {
        waitForElementVisibility(source);
        waitForElementVisibility(target);
        actions.dragAndDrop(source, target).perform();
    }

    /**
     * Selects dropdown option by visible text.
     *
     * @param dropdownElement select WebElement
     * @param visibleText option text to select
     */
    public void selectDropdownByText(WebElement dropdownElement, String visibleText) {
        waitForElementVisibility(dropdownElement);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(visibleText);
    }

    /**
     * Selects dropdown option by value attribute.
     *
     * @param dropdownElement select WebElement
     * @param value option value to select
     */
    public void selectDropdownByValue(WebElement dropdownElement, String value) {
        waitForElementVisibility(dropdownElement);
        Select select = new Select(dropdownElement);
        select.selectByValue(value);
    }

    /**
     * Selects dropdown option by its 0-based index.
     *
     * @param dropdownElement select WebElement
     * @param index option index
     */
    public void selectDropdownByIndex(WebElement dropdownElement, int index) {
        waitForElementVisibility(dropdownElement);
        Select select = new Select(dropdownElement);
        select.selectByIndex(index);
    }

    /**
     * Gets the currently selected text option from a dropdown element.
     *
     * @param dropdownElement select WebElement
     * @return text of the first selected option
     */
    public String getSelectedDropdownText(WebElement dropdownElement) {
        waitForElementVisibility(dropdownElement);
        Select select = new Select(dropdownElement);
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Switches to iframe by locator.
     *
     * @param frameLocator By locator for the iframe
     */
    public void switchToFrame(By frameLocator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    /**
     * Switches to iframe by WebElement reference.
     *
     * @param frameElement iframe WebElement
     */
    public void switchToFrame(WebElement frameElement) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    /**
     * Accepts active JavaScript alert popup.
     */
    public void acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    /**
     * Dismisses active JavaScript alert popup.
     */
    public void dismissAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }

    /**
     * Gets text message from active alert popup.
     *
     * @return alert message text
     */
    public String getAlertText() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }

    /**
     * Navigates browser back in history.
     */
    public void navigateBack() {
        webDriver.navigate().back();
    }

    /**
     * Navigates browser forward in history.
     */
    public void navigateForward() {
        webDriver.navigate().forward();
    }

    /**
     * Refreshes the current browser page.
     */
    public void refreshPage() {
        webDriver.navigate().refresh();
    }

    /**
     * Retrieves current URL from the browser.
     *
     * @return current URL string
     */
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    /**
     * Retrieves the header section WebElement.
     *
     * @return header WebElement
     */
    public WebElement getHeaderSection() {
        return headerSection;
    }

    /**
     * Retrieves the footer section WebElement.
     *
     * @return footer WebElement
     */
    public WebElement getFooterSection() {
        return footerSection;
    }

    /**
     * Clears and types text into a web input field.
     *
     * @param inputElement target input field
     * @param text text to type
     */
    public void typeText(WebElement inputElement, String text) {
        waitForElementVisibility(inputElement);
        inputElement.clear();
        inputElement.sendKeys(text);
    }
}

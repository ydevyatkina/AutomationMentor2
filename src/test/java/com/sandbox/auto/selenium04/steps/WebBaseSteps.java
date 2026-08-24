package com.sandbox.auto.selenium04.steps;

import com.sandbox.auto.selenium03.HomePage;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;

public class WebBaseSteps {
    protected WebDriver webDriver;
    protected SoftAssertions softAssert;
    private String expectedHomePageTitle = "Home Page";
    private String expectedUserNameText = "ROMAN IOVLEV";
    private HomePage homePage;

    public void setUp(WebDriver driver) {
        softAssert = new SoftAssertions();
        homePage = new HomePage(driver);
        webDriver = driver;
    }

    @Step("Step 1: Open test site by URL")
    public void openSiteCheckUrl(String uri) {
        webDriver.navigate().to(uri);
        softAssert.assertThat(webDriver.getCurrentUrl()).isEqualTo(uri);
    }

    @Step("Step 2: Assert Browser title \"Home Page\"")
    public void checkBrowserTitle() {
        softAssert.assertThat(homePage.getTitle()).isEqualTo(expectedHomePageTitle);
    }

    @Step("Step 3: Perform login")
    public void login(String username, String userPassword) {
        homePage.login(username, userPassword);
    }

    @Step("Step 4: Assert Username is logged in")
    public void checkUsername() {
        softAssert.assertThat(homePage.getUserName()).isEqualTo(expectedUserNameText);
    }

    public SoftAssertions getSoftAssert() {
        return softAssert;
    }
}

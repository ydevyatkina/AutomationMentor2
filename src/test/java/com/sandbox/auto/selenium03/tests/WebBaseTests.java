package com.sandbox.auto.selenium03.tests;

import com.sandbox.auto.selenium03.HomePage;
import com.sandbox.auto.selenium03.PropertiesService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Properties;

public abstract class WebBaseTests {
    protected WebDriver webDriver;
    protected SoftAssert softAssert;
    private String uri;
    private String username;
    private String userPassword;
    private String expectedHomePageTitle = "Home Page";
    private String expectedUserNameText = "ROMAN IOVLEV";
    private HomePage homePage;

    @BeforeClass
    public void initialSetup() {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();
            PropertiesService propertiesService = new PropertiesService();
            Properties properties = propertiesService.getProperties();
            uri = properties.get("uriJDI").toString();
            username = properties.get("username").toString();
            userPassword = properties.get("password").toString();

            softAssert = new SoftAssert();
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        }
        homePage = new HomePage(webDriver);
        openUrl();
        login();
    }

    public void openUrl() {
        //Step 1: Open test site by URL
        webDriver.navigate().to(uri);
        softAssert.assertEquals(webDriver.getCurrentUrl(), uri);

        //Step 2: Assert Browser title "Home Page"
        softAssert.assertEquals(homePage.getTitle(), expectedHomePageTitle);
    }

    public void login() {
        //Step 3: Perform login
        homePage.login(username, userPassword);

        //Step 4: Assert Username is logged in
        softAssert.assertEquals(homePage.getUserName(), expectedUserNameText);
    }

    @AfterClass
    public void classTeardown() {
        //Step: Close Browser
        webDriver.close();
    }

    @AfterSuite
    public void suitTeardown() {
        webDriver.quit();
        webDriver = null;
    }
}

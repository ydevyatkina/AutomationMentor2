package com.sandbox.auto.selenium02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private String password;
    private String homePageTitle = "Home Page";
    private String userIconId = "user-icon";
    private String nameId = "name";
    private String passwordId = "password";
    private String loginButtonId = "login-button";
    private String userNameId = "user-name";
    private String userNameText = "ROMAN IOVLEV";

    @BeforeClass
    public void initialSetup() {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();
            PropertiesService propertiesService = new PropertiesService();
            Properties properties = propertiesService.getProperties();
            uri = properties.get("uriJDI").toString();
            username = properties.get("username").toString();
            password = properties.get("password").toString();

            softAssert = new SoftAssert();
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        }
    }

    public void openUrl() {
        //Step 1: Open test site by URL
        webDriver.navigate().to(uri);
        softAssert.assertEquals(webDriver.getCurrentUrl(), uri);

        //Step 2: Assert Browser title "Home Page"
        softAssert.assertEquals(webDriver.getTitle(), homePageTitle);

    }

    public void login() {

        //Step 3: Perform login
        webDriver.findElement(By.id(userIconId)).click();
        webDriver.findElement(By.id(nameId)).sendKeys(username);
        webDriver.findElement(By.id(passwordId)).sendKeys(password);
        webDriver.findElement(By.id(loginButtonId)).click();

        //Step 4: Assert Username is logged in
        WebElement userName = webDriver.findElement(By.id(userNameId));
        softAssert.assertEquals(userName.getText(), userNameText);
        System.out.println("Before class setup has finished");
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

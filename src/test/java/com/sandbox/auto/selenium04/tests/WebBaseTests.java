package com.sandbox.auto.selenium04.tests;

import com.sandbox.auto.selenium03.PropertiesService;
import com.sandbox.auto.selenium04.AttachmentListener;
import com.sandbox.auto.selenium04.steps.WebBaseSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.time.Duration;
import java.util.Properties;

@Listeners(AttachmentListener.class)
public abstract class WebBaseTests {
    protected static WebDriver webDriver;
    private String uri;
    private String username;
    private String userPassword;

    @BeforeClass(description = "Initial setup for environment")
    public void initialSetup(ITestContext context) {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();
            PropertiesService propertiesService = new PropertiesService();
            Properties properties = propertiesService.getProperties();
            uri = properties.get("uriJDI").toString();
            username = properties.get("username").toString();
            userPassword = properties.get("password").toString();

            if (username.isEmpty() || username.equals("[MASKED]")) {
                username = System.getenv("username");
            }
            if (userPassword.isEmpty() || userPassword.equals("[MASKED]")) {
                userPassword = System.getenv("userPassword");
            }

            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            context.setAttribute("driver", webDriver);
        }
        WebBaseSteps baseSteps = new WebBaseSteps();
        baseSteps.setUp(webDriver);
        //Step 1: Open test site by URL
        baseSteps.openSiteCheckUrl(uri);
        //Step 2: Assert Browser title \"Home Page\"
        baseSteps.checkBrowserTitle();
        //Step 3: Perform login
        baseSteps.login(username, userPassword);
        //Step 4: Assert Username is logged in
        baseSteps.checkUsername();
    }

    @AfterClass(description = "Final Step: Close Browser")
    public void classTeardown() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }

    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }
}
